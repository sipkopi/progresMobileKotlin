package com.rival.tutorialloginregist
import android.content.Intent
import android.content.pm.ActivityInfo
import android.graphics.Bitmap
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import com.google.zxing.integration.android.IntentIntegrator
import com.google.zxing.integration.android.IntentResult
import com.google.zxing.BarcodeFormat
import com.google.zxing.MultiFormatWriter
import com.google.zxing.common.BitMatrix
import com.google.zxing.WriterException
import com.rival.tutorialloginregist.R

class ScanFragment : Fragment() {
    private lateinit var editText: EditText
    private lateinit var imageView: ImageView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_scan, container, false)
        val btnScan = view.findViewById<Button>(R.id.btn_scann)
        val btnQr = view.findViewById<Button>(R.id.btn_qr)
        editText = view.findViewById(R.id.edt_qrlink)
        imageView = view.findViewById(R.id.imageQR)

        btnScan.setOnClickListener {
            val scanner = IntentIntegrator.forSupportFragment(this)
            scanner.setDesiredBarcodeFormats(IntentIntegrator.QR_CODE)
            scanner.setBeepEnabled(false)
            scanner.initiateScan()
        }

        btnQr.setOnClickListener {
            generateQRCode()
        }

        activity?.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT

        return view
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        val result: IntentResult? = IntentIntegrator.parseActivityResult(requestCode, resultCode, data)

        if (result != null && result.contents != null) {
            Toast.makeText(requireContext(), "Scanned: " + result.contents, Toast.LENGTH_LONG).show()
        } else {
            Toast.makeText(requireContext(), "Cancelled", Toast.LENGTH_LONG).show()
        }
    }

    // Menambahkan metode untuk menghasilkan QR code
    private fun generateQRCode() {
        val text = editText.text.toString()

        if (text.isNotEmpty()) {
            try {
                val bitMatrix: BitMatrix = MultiFormatWriter().encode(
                    text,
                    BarcodeFormat.QR_CODE,
                    500,
                    500
                )

                val width = bitMatrix.width
                val height = bitMatrix.height
                val bmp = Bitmap.createBitmap(width, height, Bitmap.Config.RGB_565)

                for (x in 0 until width) {
                    for (y in 0 until height) {
                        bmp.setPixel(x, y, if (bitMatrix[x, y]) 0xFF000000.toInt() else 0xFFFFFFFF.toInt())
                    }
                }

                imageView.setImageBitmap(bmp)
            } catch (e: WriterException) {
                e.printStackTrace()
            }
        }
    }
}
