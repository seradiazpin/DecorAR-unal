package co.edu.unal.decorar.ui.camera

import android.app.Activity
import android.app.ActivityManager
import android.content.Context
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import co.edu.unal.decorar.R
import com.google.ar.sceneform.AnchorNode
import com.google.ar.sceneform.FrameTime
import com.google.ar.sceneform.Scene
import com.google.ar.sceneform.math.Vector3
import com.google.ar.sceneform.rendering.Material
import com.google.ar.sceneform.rendering.MaterialFactory
import com.google.ar.sceneform.rendering.ModelRenderable
import com.google.ar.sceneform.rendering.ShapeFactory
import com.google.ar.sceneform.ux.ArFragment
import com.google.ar.sceneform.ux.TransformableNode
import java.util.*

class CameraFragment : Fragment(), Scene.OnUpdateListener {

    private lateinit var cameraViewModel: CameraViewModel

    private var arFragment: ArFragment? = null
    private var tvDistance: TextView? = null
    private var cubeRenderable: ModelRenderable? = null

    private var nodeA: TransformableNode? = null
    private var nodeB: TransformableNode? = null

    var greenMaterial: Material? = null
    var originalMaterial: Material? = null

    var overlapIdle = true


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
            Toast.makeText(activity?.applicationContext, "Device not supported", Toast.LENGTH_LONG).show()
        }
        arFragment =  childFragmentManager.findFragmentById(R.id.arView) as ArFragment?
        tvDistance =  root.findViewById(R.id.tvDistance)

        initModel()

        arFragment!!.setOnTapArPlaneListener { hitResult, plane, motionEvent ->

            if (cubeRenderable != null) {

                val anchor = hitResult.createAnchor()
                val anchorNode = AnchorNode(anchor)
                anchorNode.setParent(arFragment!!.arSceneView.scene)

                if (nodeA != null && nodeB != null) {
                    clearAnchors()
                }

                val node = TransformableNode(arFragment!!.transformationSystem)
                node.renderable = cubeRenderable
                node.setParent(anchorNode)

                arFragment!!.arSceneView.scene.addChild(anchorNode)
                node.select()

                if (nodeA == null) {
                    nodeA = node
                    arFragment!!.arSceneView.scene.addOnUpdateListener(this)
                } else if (nodeB == null) {
                    nodeB = node

                }
            }
        }
        return root
    }


    @RequiresApi(Build.VERSION_CODES.N)
    private fun initModel() {

        MaterialFactory.makeOpaqueWithColor(context, com.google.ar.sceneform.rendering.Color(Color.GREEN))
            .thenAccept { material ->
                greenMaterial = material
            }

        MaterialFactory.makeOpaqueWithColor(context,
            com.google.ar.sceneform.rendering.Color(Color.RED)
        )
            .thenAccept { material ->
                val vector3 = Vector3(0.05f, 0.05f, 0.05f)
                cubeRenderable = ShapeFactory.makeCube(vector3, Vector3.zero(), material)
                originalMaterial = material

                cubeRenderable!!.isShadowCaster = false
                cubeRenderable!!.isShadowReceiver = false

            }
    }


    fun checkIsSupportedDeviceOrFinish(activity: Activity): Boolean {

        val openGlVersionString = (Objects.requireNonNull(activity.getSystemService(Context.ACTIVITY_SERVICE)) as ActivityManager)
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

    private fun clearAnchors() {

        arFragment!!.arSceneView.scene.removeChild(nodeA!!.parent!!)
        arFragment!!.arSceneView.scene.removeChild(nodeB!!.parent!!)

        nodeA = null
        nodeB = null
    }

    override fun onUpdate(frameTime: FrameTime) {

        if (nodeA != null && nodeB != null) {

            var node = arFragment!!.arSceneView.scene.overlapTest(nodeA)

            if (node != null) {

                if (overlapIdle) {
                    overlapIdle = false
                    nodeA!!.renderable!!.material = greenMaterial
                }

            } else {

                if (!overlapIdle) {
                    overlapIdle = true
                    nodeA!!.renderable!!.material = originalMaterial
                }
            }

            val positionA = nodeA!!.worldPosition
            val positionB = nodeB!!.worldPosition

            val dx = positionA.x - positionB.x
            val dy = positionA.y - positionB.y
            val dz = positionA.z - positionB.z

            //Computing a straight-line distance.
            val distanceMeters = Math.sqrt((dx * dx + dy * dy + dz * dz).toDouble()).toFloat()

            val distanceFormatted = String.format("%.2f", distanceMeters)

            tvDistance!!.text = "Distance between nodes: $distanceFormatted metres"


        }
    }

    companion object {
        private val MIN_OPENGL_VERSION = 3.0
    }


}