package tn.esprit.imageuploader

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import tn.esprit.imageuploader.databinding.ActivityImagesBinding

class ImagesActivity: AppCompatActivity() {

    private lateinit var binding: ActivityImagesBinding
    private lateinit var storageRef: StorageReference
    private lateinit var firebaseFirestore: FirebaseFirestore
    private var mList = mutableListOf<String>()
    private lateinit var adapter: ImagesAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityImagesBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initVars()
        getImages()
    }

    private fun initVars() {
        firebaseFirestore = FirebaseFirestore.getInstance()
        binding.recyclerView.setHasFixedSize(true)
        adapter = ImagesAdapter(mList)
        binding.recyclerView.adapter = adapter
    }
    private fun getImages(){
        firebaseFirestore.collection("images")
            .get().addOnSuccessListener {
                for (i in it){
                    mList.add(i.data["pic"].toString())
                }
                adapter.notifyDataSetChanged()

            }
    }
}