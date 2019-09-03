package compose.home

import android.content.Context
import android.graphics.drawable.ColorDrawable
import android.text.TextUtils.TruncateAt.END
import android.view.View
import android.widget.TextView
import com.squareup.contour.ContourLayout
import compose.theme.autoApply
import compose.theme.color
import compose.util.y
import io.reactivex.Observable
import me.saket.compose.shared.note.Note

class NoteRowView(
  context: Context,
  style: Observable<HomeStyle>
) : ContourLayout(context) {

  private val titleView = TextView(context).apply {
    maxLines = 1
    ellipsize = END
    style.map { it.noteRow.title }.autoApply(this)
    applyLayout(
        x = leftTo { parent.left() + 16.dip }.rightTo { parent.right() - 16.dip },
        y = topTo { parent.top() + 16.dip }
    )
  }

  private val bodyView = TextView(context).apply {
    maxLines = 2
    ellipsize = END
    style.map { it.noteRow.body }.autoApply(this)
    applyLayout(
        x = leftTo { titleView.left() }.rightTo { titleView.right() },
        y = topTo { titleView.bottom() + 8.dip }
    )
  }

  private val separatorView = View(context).apply {
    background = ColorDrawable(color("#62677C"))
    applyLayout(
        x = leftTo { parent.left() }.rightTo { parent.right() },
        y = topTo { bodyView.bottom() + 16.dip }.heightOf { 1.dip.y }
    )
  }

  init {
    heightOf { separatorView.bottom() }
  }

  fun render(note: Note) {
    titleView.text = note.title
    bodyView.text = note.body
  }
}