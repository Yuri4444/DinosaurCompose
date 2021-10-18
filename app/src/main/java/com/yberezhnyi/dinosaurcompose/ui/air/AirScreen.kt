package com.yberezhnyi.dinosaurcompose.ui.air

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.yberezhnyi.dinosaurcompose.R
import com.yberezhnyi.dinosaurcompose.StatusBar
import com.yberezhnyi.dinosaurcompose.Toolbar
import com.yberezhnyi.dinosaurcompose.ui.theme.GreenDark
import com.yberezhnyi.dinosaurcompose.ui.theme.RedDark

@Composable
fun AirScreen() {
    Box(
        modifier = Modifier.fillMaxSize(),
//        contentAlignment = Alignment.Center
    ) {
        Text(text = stringResource(id = R.string.air))
        StatusBar(color = GreenDark)
//        Toolbar(color = RedDark, title = stringResource(id = R.string.air_dinosaurs))
    }
}