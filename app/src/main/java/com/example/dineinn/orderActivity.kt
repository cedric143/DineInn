package com.example.dineinn

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.Paint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ImageButton
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.Spinner
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class orderActivity : AppCompatActivity() {
    private lateinit var pizzaSpinner:Spinner
    private lateinit var radioButton:RadioButton
    private lateinit var radioGroup:RadioGroup
    private lateinit var counterTextView: TextView
    private lateinit var radiopayGroup: RadioGroup
    private lateinit var radiopaybutton:RadioButton
    private lateinit var dbRef:DatabaseReference


    @SuppressLint("MissingInflatedId", "CutPasteId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_order)
        dbRef= FirebaseDatabase.getInstance().reference
        pizzaSpinner=findViewById(R.id.pizzaSpinner)
        counterTextView=findViewById(R.id.counterTextView)
        radioGroup=findViewById(R.id.sizeRadioGroup)
        radiopayGroup=findViewById(R.id.paymentRadioGroup)




        val configurability =findViewById<Button>(R.id.cnfmorderbutton)

        configurability.setOnClickListener {
            pushDataToFirebase()
        }

        val pizzaOptions = arrayOf("Chicken Pizza", "Hawaiian Pizza", "Mushroom Pizza", "Peri-Peri Pizza", "Pepperoni Pizza")

        val pizzaSpinner = findViewById<Spinner>(R.id.pizzaSpinner)
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, pizzaOptions)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        pizzaSpinner.adapter = adapter

        pizzaSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View?, position: Int, id: Long) {
                val selectedPizza = pizzaOptions[position]
                // Do something with the selected pizza option
                Toast.makeText(this@orderActivity, "Selected pizza: $selectedPizza", Toast.LENGTH_SHORT).show()
            }

            override fun onNothingSelected(parent: AdapterView<*>) {
                // Handle the case when no pizza option is selected
            }
        }
        val counterTextView = findViewById<TextView>(R.id.counterTextView)
        val incrementButton = findViewById<ImageButton>(R.id.incrementButton)
        val decrementButton = findViewById<ImageButton>(R.id.decrementButton)

        var counter = 0

        incrementButton.setOnClickListener {
            counter++
            counterTextView.text = counter.toString()
        }

        decrementButton.setOnClickListener {
            if (counter > 0) {
                counter--
                counterTextView.text = counter.toString()
            }
        }

        val radioGroup = findViewById<RadioGroup>(R.id.sizeRadioGroup)

        radioGroup.setOnCheckedChangeListener { group, checkedId ->
            val selectedRadioButton = findViewById<RadioButton>(checkedId)
            val selectedSize = selectedRadioButton.text.toString()
            // Do something with the selected size (e.g., update UI, store selection)
            Toast.makeText(this@orderActivity, "Selected size: $selectedSize", Toast.LENGTH_SHORT).show()
        }

        radiopayGroup.setOnCheckedChangeListener { group, checkedId ->
            val selectedRadioButton = findViewById<RadioButton>(checkedId)
            val selectedPay = selectedRadioButton.text.toString()
            // Do something with the selected size (e.g., update UI, store selection)
            Toast.makeText(this@orderActivity, "mode of payment: $selectedPay", Toast.LENGTH_SHORT).show()
        }



        val underlinedpizzasize = findViewById<TextView>(R.id.pizza_size)
        underlinedpizzasize.paintFlags = underlinedpizzasize.paintFlags or Paint.UNDERLINE_TEXT_FLAG

        val underlinedpizzanumber = findViewById<TextView>(R.id.pizza_number)
        underlinedpizzanumber.paintFlags = underlinedpizzanumber.paintFlags or Paint.UNDERLINE_TEXT_FLAG

        val underlinedpizzatype = findViewById<TextView>(R.id.pizza_type)
        underlinedpizzatype.paintFlags = underlinedpizzatype.paintFlags or Paint.UNDERLINE_TEXT_FLAG






    }

    private fun pushDataToFirebase() {
        // Get selected item from Spinner
        val selectedItem = pizzaSpinner.selectedItem.toString()

        // Get selected Radio Button from Radio Group
        val radioButtonId = radioGroup.checkedRadioButtonId
        radioButton = findViewById(radioButtonId)
        val radioButtonText = radioButton.text.toString()

        val radiogrppay = radiopayGroup.checkedRadioButtonId
        radiopaybutton = findViewById(radiogrppay)
        val radioButtonPay = radiopaybutton.text.toString()

        // Get counter value from TextView
        val counterValue = counterTextView.text.toString().toInt()

        // Create a data object to push to Firebase
        val order = Order(selectedItem, radioButtonText, counterValue,radioButtonPay)

        // Push data to Firebase
        val dataReference = dbRef.push() // This creates a new unique key for each entry
        dataReference.setValue(order)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    // Display success message
                    Toast.makeText(this, "Order placed successfully", Toast.LENGTH_SHORT).show()
                    val intent=Intent(this,confirmedActivity::class.java)
                    startActivity(intent)
                } else {
                    // Display failure message
                    Toast.makeText(this, "Failed to place order", Toast.LENGTH_SHORT).show()
                }
            }

    }

    data class Order(
        val type: String = "",
        val size: String = "",
        val Number: Int = 0,
        val Pay: String = "",


    )


}