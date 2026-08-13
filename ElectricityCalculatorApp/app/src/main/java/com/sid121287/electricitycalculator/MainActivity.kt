package com.sid121287.electricitycalculator

import android.annotation.SuppressLint
import android.os.Bundle
import android.print.PrintAttributes
import android.print.PrintManager
import android.webkit.WebChromeClient
import android.webkit.WebView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var webView: WebView

    @SuppressLint("SetJavaScriptEnabled")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        webView = WebView(this)
        setContentView(webView)

        webView.settings.javaScriptEnabled = true
        webView.settings.domStorageEnabled = true
        webView.webChromeClient = object : WebChromeClient() {
            override fun onCreateWindow(
                view: WebView?,
                isDialog: Boolean,
                isUserGesture: Boolean,
                resultMsg: android.os.Message?
            ): Boolean = false

            override fun onJsBeforeUnload(
                view: WebView?,
                url: String?,
                message: String?,
                result: android.webkit.JsResult?
            ): Boolean {
                result?.confirm()
                return true
            }
        }

        // Support the on-page "Print" button (which calls window.print()) via the
        // native Android print dialog.
        webView.addJavascriptInterface(PrintBridge(), "AndroidPrint")
        webView.evaluateJavascript(
            "document.getElementById('print').addEventListener('click', function(e){ AndroidPrint.print(); }, true);",
            null
        )

        webView.loadUrl("file:///android_asset/calculator.html")
    }

    override fun onBackPressed() {
        if (webView.canGoBack()) {
            webView.goBack()
        } else {
            super.onBackPressed()
        }
    }

    inner class PrintBridge {
        @android.webkit.JavascriptInterface
        fun print() {
            runOnUiThread {
                val printManager = getSystemService(PRINT_SERVICE) as PrintManager
                val adapter = webView.createPrintDocumentAdapter("Electricity_Bill")
                printManager.print(
                    "Electricity_Bill",
                    adapter,
                    PrintAttributes.Builder().build()
                )
            }
        }
    }
}
