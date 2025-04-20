package com.example.passwordmanager

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView


class CredentialAdapter(
    private val context: Context,
    private val credentialList: List<Credential>
) : RecyclerView.Adapter<CredentialAdapter.CredentialViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CredentialViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_credential, parent, false)
        return CredentialViewHolder(view)
    }

    override fun onBindViewHolder(holder: CredentialViewHolder, position: Int) {
        val credential = credentialList[position]

        // Format the text for all fields with labels
        holder.websiteTextView.text = "Website: ${credential.website}"
        holder.usernameTextView.text = "Username: ${credential.username}"
        holder.passwordTextView.text = "Password: ${credential.password}"

        // Set up the delete button's onClickListener
        holder.deleteButton.setOnClickListener {
            // Handle the delete action (e.g., remove from the list)
            (credentialList as MutableList).removeAt(position)
            notifyItemRemoved(position)
            Toast.makeText(context, "Credential deleted!", Toast.LENGTH_SHORT).show()
        }

        // Set up the copy button's onClickListener
        holder.copyButton.setOnClickListener {
            val clipboard = context.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
            val clip = ClipData.newPlainText("Password", credential.password)
            clipboard.setPrimaryClip(clip)
            Toast.makeText(context, "Password copied!", Toast.LENGTH_SHORT).show()
        }
    }


    override fun getItemCount(): Int {
        return credentialList.size
    }

    class CredentialViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val websiteTextView: TextView = itemView.findViewById(R.id.websiteTextView)
        val usernameTextView: TextView = itemView.findViewById(R.id.usernameTextView)
        val passwordTextView: TextView = itemView.findViewById(R.id.passwordTextView)
        val copyButton: ImageButton = itemView.findViewById(R.id.copyButton)
        val deleteButton: ImageButton = itemView.findViewById(R.id.deleteButton)  // Add this line
    }
}
