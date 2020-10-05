package co.edu.unal.decorar.ui.camera

import android.app.Activity
import android.app.ActivityManager
import android.content.ContentValues.TAG
import android.content.Context
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import co.edu.unal.decorar.R
import com.google.ar.core.Plane
import com.google.ar.sceneform.AnchorNode
import com.google.ar.sceneform.FrameTime
import com.google.ar.sceneform.Node
import com.google.ar.sceneform.Scene
import com.google.ar.sceneform.math.Vector3
import com.google.ar.sceneform.rendering.*
import com.google.ar.sceneform.ux.ArFragment
import com.google.ar.sceneform.ux.TransformableNode
import java.util.*
import java.util.concurrent.CompletableFuture
import java.util.function.Consumer
import java.util.function.Function
import kotlin.collections.ArrayList

class CameraFragment : Fragment(), Scene.OnUpdateListener {

    private lateinit var cameraViewModel: CameraViewModel

    private var arFragment: ArFragment? = null
    private var renderableObject: ModelRenderable? = null
    private var nodesList: ArrayList<TransformableNode>? = null

    var originalMaterial: Material? = null
    var planeRenderable: ModelRenderable? = null
    var materialTexture: Texture? = null


    /**
     * Load camera fragment component's
     */
    @RequiresApi(Build.VERSION_CODES.N)
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        cameraViewModel =
            ViewModelProviders.of(this).get(CameraViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_camera, container, false)
        if (!checkIsSupportedDeviceOrFinish(activity as Activity)) {
            Toast.makeText(activity?.applicationContext, "Device not supported", Toast.LENGTH_LONG)
                .show()
        }
        nodesList = ArrayList<TransformableNode>()
        arguments?.getSerializable("item")
        arFragment = childFragmentManager.findFragmentById(R.id.arView) as ArFragment?
        getModel(2)
        getTexture(2)
        setArFragmentListener()

        return root
    }

    /**
     *  Get Model Id from viewModel
     */

    @RequiresApi(Build.VERSION_CODES.N)
    private fun getModel(id: Int) {
        cameraViewModel.elements.observe(viewLifecycleOwner, androidx.lifecycle.Observer {
            it[id]?.let { it1 -> loadModel(it1) }
        })
    }

    /**
     *  Get Texture Id from viewModel
     */
    @RequiresApi(Build.VERSION_CODES.N)
    private fun getTexture(id: Int) {
        cameraViewModel.floors.observe(viewLifecycleOwner, androidx.lifecycle.Observer {
            it[id]?.let { it1 -> loadFloor(it1) }
        })
    }

    /**
     * add plane with floor texture to model
     */
    @RequiresApi(Build.VERSION_CODES.N)
    private fun addPlaneFloorToModel(plane: Plane, anchorNode: AnchorNode) {
        if (plane.type === Plane.Type.HORIZONTAL_UPWARD_FACING) {
            Texture.builder()
                .setSource(context, R.drawable.floortexture2)
                .build()
                .thenAccept { texture ->
                    materialTexture = texture
                }
            MaterialFactory.makeOpaqueWithTexture(context, materialTexture).thenAccept { material ->
                val surfaceMaterial = material.makeCopy()
                val cubeSize = Vector3(0.5f, 0f, 0.5f)
                val cubePosition = Vector3(0f, 0f, 0f)

                planeRenderable = ShapeFactory.makeCube(cubeSize, cubePosition, surfaceMaterial)
            }

            planeRenderable?.material ?: materialTexture
            planeRenderable?.let {
                createPlaneNode(
                    anchorNode,
                    it, Vector3(0.0f, 0.0f, 0.0f)
                )
            }
        }
    }
    @RequiresApi(Build.VERSION_CODES.N)
    private fun setArFragmentListener(){
        arFragment!!.setOnTapArPlaneListener { hitResult, plane, motionEvent ->

            if (renderableObject != null) {

                val anchor = hitResult.createAnchor()
                val anchorNode = AnchorNode(anchor)
                anchorNode.setParent(arFragment!!.arSceneView.scene)
                val node = TransformableNode(arFragment!!.transformationSystem)

                node.renderable = renderableObject
                node.setParent(anchorNode)
                addPlaneFloorToModel(plane, anchorNode)
                if (nodesList?.size!! == 2) {
                    clearAnchors()
                }
                node.scaleController.maxScale = 0.5f;
                node.scaleController.minScale = 0.02f;
                arFragment!!.arSceneView.scene.addChild(anchorNode)
                node.select()
                if (nodesList!!.size < 2) {
                    nodesList!!.add(node)
                    arFragment!!.arSceneView.scene.addOnUpdateListener(this)
                }
            }


        }
    }

    /**
     * Create a plane under the 3D Model
     */
    private fun createPlaneNode(
        anchorNode: AnchorNode,
        renderable: ModelRenderable,
        localPosition: Vector3
    ): Node {
        val shape = Node()
        shape.setParent(anchorNode)
        shape.renderable = renderable
        shape.localPosition = localPosition

        return shape
    }

    /**
     * Load the 3D model bu id
     */
    @RequiresApi(Build.VERSION_CODES.N)
    private fun loadModel(id: Int) {
        MaterialFactory.makeOpaqueWithColor(
            context,
            com.google.ar.sceneform.rendering.Color(Color.RED)
        )
            .thenAccept { material ->
                ModelRenderable.builder() // To load as an asset from the 'assets' folder ('src/main/assets/andy.sfb'):
                    .setSource(context, id)
                    .build()
                    .thenAccept(Consumer { renderable: ModelRenderable ->
                        renderableObject = renderable
                    })
                    .exceptionally(
                        Function<Throwable, Void?> { throwable: Throwable? ->
                            Log.e(TAG, "Unable to load Renderable.", throwable)
                            null
                        }
                    )

                originalMaterial = material

                renderableObject!!.isShadowCaster = false
                renderableObject!!.isShadowReceiver = false

            }
    }

    /**
     * Check If the device have OpenGL
     */
    private fun checkIsSupportedDeviceOrFinish(activity: Activity): Boolean {

        val openGlVersionString =
            (Objects.requireNonNull(activity.getSystemService(Context.ACTIVITY_SERVICE)) as ActivityManager)
                .deviceConfigurationInfo
                .glEsVersion
        if (java.lang.Double.parseDouble(openGlVersionString) < MIN_OPENGL_VERSION) {
            Toast.makeText(activity, "Sceneform requires OpenGL ES 3.0 or later", Toast.LENGTH_LONG)
                .show()
            activity.finish()
            return false
        }
        return true
    }

    /**
     * Load Floor Texture by id
     */
    @RequiresApi(Build.VERSION_CODES.N)
    private fun loadFloor(id: Int) {
        // Build texture sampler
        val sampler: Texture.Sampler = Texture.Sampler.builder()
            .setMinFilter(Texture.Sampler.MinFilter.LINEAR)
            .setMagFilter(Texture.Sampler.MagFilter.LINEAR)
            .setWrapMode(Texture.Sampler.WrapMode.REPEAT).build()

        // Build texture with sampler
        val trigrid: CompletableFuture<Texture> = Texture.builder()
            .setSource(context, id)
            .setSampler(sampler).build()

        // Set plane texture
        arFragment?.arSceneView?.planeRenderer?.material?.thenAcceptBoth(trigrid)
        { material, texture ->
            material.setTexture(
                PlaneRenderer.MATERIAL_TEXTURE,
                texture
            )
        }
    }

    /**
     * Clear all anchors on the nodeList
     */
    private fun clearAnchors() {
        for (nodeElement in nodesList!!) {
            arFragment!!.arSceneView.scene.removeChild(nodeElement!!.parent!!)
        }
        nodesList = ArrayList<TransformableNode>()
    }

    override fun onUpdate(frameTime: FrameTime) {

    }

    companion object {
        private val MIN_OPENGL_VERSION = 3.0
    }


}