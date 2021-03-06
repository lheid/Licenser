package com.marcoscg.licensersample

import android.content.DialogInterface
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.marcoscg.licenser.Library
import com.marcoscg.licenser.License
import com.marcoscg.licenser.LicenserDialog
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private var licenserDialog: LicenserDialog? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val customLicenseHtmlContent = "Licensed under the Custom License, V.3" +
                "<br><br>LICENSE DESCRIPTION HERE<br>REMEMBER TO USE HTML TAGS!"

        licenserDialog = LicenserDialog(this, R.style.DialogStyle)
                .setTitle("Licenses")
                .setCancelable(true)
                .setBackgroundColor(ContextCompat.getColor(this, R.color.colorPrimary))
                .setCustomNoticeTitle("Notices for files:")
                .setLibrary(Library("Android Support Libraries",
                        null,
                        License.APACHE2))
                .setLibrary(Library("Example Library",
                        "https://github.com/marcoscgdev",
                        License.APACHE2))
                .setLibrary(Library("Licenser",
                        "https://github.com/marcoscgdev/Licenser",
                        License.MIT))
                .setLibrary(Library("Custom library",
                        null,
                        License("CUSTOM_LIC", customLicenseHtmlContent))) // use license code like APACHE2 or MIT
                .setPositiveButton(android.R.string.ok, DialogInterface.OnClickListener { dialogInterface, i ->
                    // TODO: 11/02/2018
                })

        //val apache2Libraries = licenserDialog?.apache2Libraries

        bt_show_dialog.setOnClickListener {
            licenserDialog?.show()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_github -> {
                startActivity(Intent(Intent.ACTION_VIEW, Uri.parse("https://github.com/marcoscgdev/Licenser")))
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}