package cz.tka.app.todo.ui.elements

import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource

@Composable
fun InfoElement(
    value: String?,
    hint: String,
    leadingIcon: Int,
    onClick: () -> Unit,
    onClearClick: () -> Unit){

    val interactionSource = remember { MutableInteractionSource() }
    val isPressed: Boolean by interactionSource.collectIsPressedAsState()

    val focusManager = LocalFocusManager.current

    if (isPressed) {
        LaunchedEffect(isPressed){
            onClick()
        }
    }

    OutlinedTextField(
        value = if (value != null) value else "",
        onValueChange = {},
        interactionSource = interactionSource,
        leadingIcon = {
            Icon(
                painter = painterResource(id = leadingIcon),
                contentDescription = null
            )
        },
        trailingIcon = if (value != null) {
            {
                IconButton(
                    onClick = {
                        onClearClick()
                        focusManager.clearFocus()
                    }
                ){
                    Icon(
                        painter = rememberVectorPainter(Icons.Filled.Clear),
                        contentDescription = null
                    )
                }
            }
        }else {
            null
        },
        label = { Text(text = hint) },
        readOnly = true,
        modifier = Modifier
            .fillMaxWidth()
    )
}
