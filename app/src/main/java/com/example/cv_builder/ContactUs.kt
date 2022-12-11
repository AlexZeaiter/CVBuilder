package com.example.cv_builder

import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.fragment_contact_us.*

class ContactUs : Fragment() {
    lateinit var dialBtn : Button
    lateinit var emailBtn : Button

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater!!.inflate(R.layout.fragment_contact_us, container, false)
        dialBtn = view.findViewById(R.id.phone) as Button
        emailBtn = view.findViewById(R.id.emailus) as Button
        dialBtn.setOnClickListener {
            val i = Intent()
            i.action = Intent.ACTION_DIAL
            i.data = Uri.parse("tel:$+11234567890") //tel: data representation of telephony number
            startActivity(i)
        }
        emailBtn.setOnClickListener{
            try {
                val intent = Intent(Intent.ACTION_SENDTO)
                intent.data = Uri.parse("mailto:") // only email apps should handle this

                intent.putExtra(Intent.EXTRA_EMAIL, "CVDemo@Gmail.Com")
                intent.putExtra(Intent.EXTRA_SUBJECT, "ContactUS")
                startActivity(Intent.createChooser(intent, "Finish the action with:"));
            }
            catch (e: PackageManager.NameNotFoundException) {

            }

        }
        return view
    }
    private fun dial(view: View){
        val i = Intent()
        i.action = Intent.ACTION_DIAL
        i.data = Uri.parse("tel:$+11234567890") //tel: data representation of telephony number
        startActivity(i)
    }

    private fun email(view: View) {
        try {
            val intent = Intent(Intent.ACTION_SENDTO)
            intent.data = Uri.parse("mailto:") // only email apps should handle this

            intent.putExtra(Intent.EXTRA_EMAIL, "CVDemo@Gmail.Com")
            intent.putExtra(Intent.EXTRA_SUBJECT, "ContactUS")
            startActivity(intent)
        }
        catch (e: PackageManager.NameNotFoundException) {

        }
    }
}