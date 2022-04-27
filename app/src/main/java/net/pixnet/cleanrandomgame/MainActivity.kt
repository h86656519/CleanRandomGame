package net.pixnet.cleanrandomgame

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import net.pixnet.cleanrandomgame.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private var binding: ActivityMainBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding!!.root)

        toFirstPage()
    }

    private fun toFirstPage() {
        val firstFragment = FirstFragment.newInstance()
        val fragmentTransaction = supportFragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.content_fragment, firstFragment)
        fragmentTransaction.addToBackStack(firstFragment.javaClass.name)
        fragmentTransaction.commit()
    }

    fun toSecondPage(row: Int, column: Int) {
        val secondFragment = SecondFragment.newInstance(row, column)
        val fragmentTransaction = supportFragmentManager.beginTransaction()
        fragmentTransaction.add(R.id.content_fragment, secondFragment)
        fragmentTransaction.addToBackStack(secondFragment.javaClass.name)
        fragmentTransaction.commit()
    }

    fun showErrorToast(){
        Toast.makeText(this,resources.getText(R.string.error_input_message),Toast.LENGTH_LONG).show()
    }
}