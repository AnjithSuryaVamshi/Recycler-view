package com.example.recyclerview

import android.app.Dialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.view.View
import android.view.Window
import android.widget.Adapter
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.result.contract.ActivityResultContracts
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity() {
    lateinit var Dimg : ImageView
    lateinit var Dname: TextView
    lateinit var Cnum: TextView
    lateinit var DbtnImg : Button
    lateinit var  DAddBtn : Button
    val listOfContacts = mutableListOf<Contact>()
    lateinit var dialog: Dialog
    lateinit var myAdapter : Custom_Adapter
    lateinit var Rview : RecyclerView
    lateinit var fab  :FloatingActionButton
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Rview = findViewById(R.id.Rview)
        myAdapter = Custom_Adapter(listOfContacts)
        Rview.layoutManager = LinearLayoutManager(this)
        Rview.adapter=  myAdapter

        fab = findViewById(R.id.fabBtn)


        fab.setOnClickListener {  showDialog()}

    }
    private fun showDialog(){
         dialog = Dialog(this)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setCancelable(false)
        dialog.setContentView(R.layout.dialog)
        Dname = dialog.findViewById(R.id.Dname)
        Cnum = dialog.findViewById(R.id.Dnum)
        Dimg  =  dialog.findViewById(R.id.imageView)
        DbtnImg = dialog.findViewById(R.id.buttonImg)
        DAddBtn = dialog.findViewById(R.id.BtnCon)
        DbtnImg.setOnClickListener {
            val ImgIntent = Intent(Intent.ACTION_PICK,MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
            startActivityForResult(ImgIntent,101)
        }

        dialog.show()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(requestCode==101 && resultCode== RESULT_OK){
            Dimg.visibility = View.VISIBLE
            Dimg.setImageURI(data?.data)

        }
        DAddBtn.setOnClickListener {
            val name = Dname.text.toString()
            val num = Cnum.text.toString()
            val img = data?.data
            val contact = Contact(
                img!!,
                name,
                num

            )
            listOfContacts.add(contact)
            myAdapter.notifyDataSetChanged()
            dialog.dismiss()

        }
    }
}