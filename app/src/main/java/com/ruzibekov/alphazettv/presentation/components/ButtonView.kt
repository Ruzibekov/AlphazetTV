package com.ruzibekov.alphazettv.presentation.components

import android.content.res.Configuration
import androidx.annotation.StringRes
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.tv.material3.ExperimentalTvMaterial3Api
import androidx.tv.material3.MaterialTheme
import androidx.tv.material3.Text
import com.ruzibekov.alphazettv.R
import com.ruzibekov.alphazettv.presentation.theme.AlphazetTVTheme

object ButtonView {

    @OptIn(ExperimentalTvMaterial3Api::class)
    @Composable
    fun Default(onClick: () -> Unit, @StringRes textRes: Int) {
        Box(
            modifier = Modifier
                .clip(RoundedCornerShape(16.dp))
                .clickable(onClick = onClick)
                .background(MaterialTheme.colorScheme.primary)
                .padding(horizontal = 24.dp, vertical = 12.dp),
            contentAlignment = Alignment.Center,
        ) {

            Text(
                text = stringResource(textRes),
                fontSize = 18.sp
            )
        }
    }
}

@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_NO)
@Composable
private fun ButtonPreviewLight() {
    AlphazetTVTheme { ButtonView.Default({}, R.string.done_btn) }
}

@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun ButtonPreviewDark() {
    AlphazetTVTheme { ButtonView.Default({}, R.string.done_btn) }
}
