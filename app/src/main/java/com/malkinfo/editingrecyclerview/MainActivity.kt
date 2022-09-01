package com.malkinfo.editingrecyclerview
import android.content.SharedPreferences
import android.content.res.Configuration
import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuItem
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.appcompat.widget.SwitchCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.malkinfo.editingrecyclerview.model.UserData
import com.malkinfo.editingrecyclerview.view.UserAdapter
import java.util.*


class MainActivity : AppCompatActivity() {


    private lateinit var addsBtn:FloatingActionButton
    private lateinit var recv:RecyclerView
    private lateinit var userList:ArrayList<UserData>
    private lateinit var userAdapter:UserAdapter



//    private lateinit var switchCompat: SwitchCompat
    private lateinit var sharedPreferences: SharedPreferences



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        /**set List*/
        userList = ArrayList()
        /**set find Id*/
        addsBtn = findViewById(R.id.addingBtn)
        recv = findViewById(R.id.mRecycler)
        /**set Adapter*/
        userAdapter = UserAdapter(this,userList)
        /**setRecycler view Adapter*/
        recv.layoutManager = LinearLayoutManager(this)
        recv.adapter = userAdapter


        userList.add(UserData("State: Aguascalientes","State No. :1"))
        userList.add(UserData("State: Baja California","State No. :2"))
        userList.add(UserData("State: Baja California Sur","State No. :3"))
        userList.add(UserData("State: Campeche","State No. :4"))
        userList.add(UserData("State: Chiapas","State No. :5"))
        userList.add(UserData("State: Chihuahua","State No. :6"))
        userList.add(UserData("State: Ciudad de México","State No. :7"))
        userList.add(UserData("State: Coahuila","State No. :8"))
        userList.add(UserData("State: Colima","State No. :9"))
        userList.add(UserData("State: Durango","State No. :10"))
        userList.add(UserData("State: Guanajuato","State No. :11"))
        userList.add(UserData("State: Guerrero","State No. :12"))
        userList.add(UserData("State: Hidalgo","State No. :13"))
        userList.add(UserData("State: Jalisco","State No. :14"))
        userList.add(UserData("State: México","State No. :15"))
        userList.add(UserData("State: Michoacán","State No. :16"))
        userList.add(UserData("State: Morelos","State No. :17"))
        userList.add(UserData("State: Nayarit","State No. :18"))
        userList.add(UserData("State: Nuevo León","State No. :19"))
        userList.add(UserData("State: Oaxaca","State No. :20"))
        userList.add(UserData("State: Puebla","State No. :21"))
        userList.add(UserData("State: Querétaro","State No. :22"))
        userList.add(UserData("State: Quintana Roo","State No. :23"))
        userList.add(UserData("State: San Luis Potosí","State No. :24"))
        userList.add(UserData("State: Sinaloa","State No. :25"))
        userList.add(UserData("State: Sonora","State No. :26"))
        userList.add(UserData("State: Tabasco","State No. :27"))
        userList.add(UserData("State: Tamaulipas","State No. :28"))
        userList.add(UserData("State: Tlaxcala","State No. :29"))
        userList.add(UserData("State: Veracruz","State No. :30"))
        userList.add(UserData("State: Yucatán","State No. :31"))
        userList.add(UserData("State: Zacatecas","State No. :32"))


        /**set Dialog*/
        addsBtn.setOnClickListener { addInfo() }

//        switchCompat = findViewById(R.id.switchCompat)

//
//        sharedPreferences = getSharedPreferences("night", 0)
//        val booleanValue = sharedPreferences.getBoolean("night_mode", false)
//        if (booleanValue) {
//            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
//            switchCompat.isChecked = true
//        }
//
//        switchCompat.setOnCheckedChangeListener { buttonView, isChecked ->
//            if (isChecked) {
//                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
//                switchCompat.isChecked = true
//                val editor = sharedPreferences.edit()
//                editor.putBoolean("night_mode", true)
//                editor.commit()
//            } else {
//                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
//                switchCompat.isChecked = false
//                val editor = sharedPreferences.edit()
//                editor.putBoolean("night_mode", false)
//                editor.commit()
//            }
//        }


        val currentNightMode =  Configuration.UI_MODE_NIGHT_MASK
        when (currentNightMode) {
            Configuration.UI_MODE_NIGHT_NO -> {} // Night mode is not active, we're using the light theme
            Configuration.UI_MODE_NIGHT_YES -> {} // Night mode is active, we're using dark theme
        }

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.nav_menu, menu)
        return true
    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.nav_select -> Toast.makeText(this, "Select", Toast.LENGTH_SHORT).show()
            R.id.nav_select_all -> Toast.makeText(this, "Select All", Toast.LENGTH_SHORT).show()
            R.id.nav_delete -> {
                userList.removeAll(userList)
                userAdapter.notifyDataSetChanged()
                Toast.makeText(this, "Delete All", Toast.LENGTH_SHORT).show()
            }
        }

        return super.onOptionsItemSelected(item)
    }



    private fun addInfo() {


        val inflter = LayoutInflater.from(this)
        val v = inflter.inflate(R.layout.add_item,null)
        /**set view*/
        val userName = v.findViewById<EditText>(R.id.userName)
        val userNo = v.findViewById<EditText>(R.id.userNo)

        val addDialog = AlertDialog.Builder(this)

        addDialog.setView(v)
        addDialog.setPositiveButton("Ok"){
            dialog,_->
            val names = userName.text.toString()
            val number = userNo.text.toString()
            userList.add(UserData("State: $names","State No. : $number"))
            userAdapter.notifyDataSetChanged()
            Toast.makeText(this,"Adding User Information Success",Toast.LENGTH_SHORT).show()
            dialog.dismiss()
        }
        addDialog.setNegativeButton("Cancel"){
            dialog,_->
            dialog.dismiss()
            Toast.makeText(this,"Cancel",Toast.LENGTH_SHORT).show()

        }
        addDialog.create()
        addDialog.show()
    }
    /**ok now run this */


    private fun createDummyData(offset:Int,limit:Int):MutableList<UserData>{
        val list    = mutableListOf<UserData>()
        var data: UserData
        for(i in offset..(offset + limit)){
            data= UserData(
                userMb = UUID.randomUUID().toString().replace("-",""),
                userName = "content index $i"
            )
            list.add(data)
        }
        return list
    }




}