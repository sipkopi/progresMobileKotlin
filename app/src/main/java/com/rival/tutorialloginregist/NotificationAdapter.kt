import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.rival.tutorialloginregist.ItemNotifikasi
import com.rival.tutorialloginregist.R

class NotificationAdapter : RecyclerView.Adapter<NotificationAdapter.NotificationViewHolder>() {
    private val notifikasiList = mutableListOf<ItemNotifikasi>()

    inner class NotificationViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val titleTextView: TextView = itemView.findViewById(R.id.titleET)
        val messageTextView: TextView = itemView.findViewById(R.id.messageET)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NotificationViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.notification_item, parent, false)
        return NotificationViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: NotificationViewHolder, position: Int) {
        val currentItem = notifikasiList[position]
        holder.titleTextView.text = currentItem.judul
        holder.messageTextView.text = currentItem.pesan
    }

    override fun getItemCount() = notifikasiList.size

    fun addNotification(notifikasi: ItemNotifikasi) {
        notifikasiList.add(notifikasi)
        notifyItemInserted(notifikasiList.size - 1)
    }
}
