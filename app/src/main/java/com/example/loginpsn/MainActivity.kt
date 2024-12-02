package com.example.loginpsn

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.OutlinedButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.sp
import com.example.loginpsn.ui.theme.LoginPSNTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            LoginPSNTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    var textEmail by rememberSaveable { mutableStateOf("")}
                    var textPasswd by rememberSaveable { mutableStateOf("")}
                    var isValid by rememberSaveable { mutableStateOf(true) }
                    var dismissed by rememberSaveable { mutableStateOf(true) }

                    val id = "usuario@gmail.com"
                    val password = "1234"


                    Login(
                        Modifier.padding(innerPadding)
                        ,isValid
                        ,textEmail
                        ,textPasswd
                        ,dismissed
                        ,{textPasswd = it}
                        ,{textEmail = it}
                        ,{
                            isValid = !(textEmail!=id || textPasswd!=password)
                            if (isValid){
                                dismissed=false
                            }else{
                                textPasswd=""
                            }
                        }
                        ,{dismissed = !dismissed}
                    )
                }
            }
        }
    }
}

@Composable
fun Login(
    modifier: Modifier
    , isValid: Boolean
    , textEmail:String
    , textPasswd:String
    , dismissed :Boolean
    , changePasswd:(String) ->Unit
    , changeEmail:(String) ->Unit
    ,onClick: () -> Unit
    ,dismiss: () -> Unit
    ){

    Column(
        modifier = modifier
            .fillMaxSize()
            .paint(
                painter = painterResource(R.drawable.background),
                contentScale = ContentScale.FillBounds
            )
    ) {

        TitleText(
            Modifier
                .fillMaxWidth()
                .padding(20.dp, 20.dp, 0.dp, 5.dp),
            contentAlignment = Alignment.BottomStart,
            text = "Sign In to PlayStation Network"
        )

        HorizontalDivider(
            modifier = Modifier
                .background(colorResource(R.color.lightGray))
                .fillMaxWidth()
                .height(1.dp))

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(0.dp, 30.dp, 0.dp, 0.dp),
        ) {
            Row(modifier = Modifier.align(Alignment.CenterHorizontally)) {
                Image(
                    modifier = Modifier
                        .size(65.dp, 55.dp)
                        .padding(5.dp),
                    painter = painterResource(R.drawable.logo),
                    contentScale = ContentScale.FillBounds,
                    contentDescription = "Logo"
                )

                Text(
                    modifier = Modifier
                        .padding(5.dp),
                    text = "PlayStation Network",
                    color = Color.White,
                    fontSize = 30.sp,
                    fontWeight = FontWeight.Bold
                )
            }

            Text(modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(5.dp, 30.dp, 5.dp, 15.dp),
                text = "Go online by signing in to PlayStation Network",
                color = Color.White,
                fontSize = 17.sp)

            CampoDeTexto(
                textEmail,
                isValid,
                changeEmail,
                Modifier.align(Alignment.CenterHorizontally),
                stringResource(R.string.email),
                stringResource(R.string.sign_in_id_email_address),
                VisualTransformation.None
            )

            CampoDeTexto(
                textPasswd,
                isValid,
                changePasswd,
                Modifier.align(Alignment.CenterHorizontally),
                stringResource(R.string.password),
                stringResource(R.string.password),
                PasswordVisualTransformation()
            )

            Spacer(Modifier.size(50.dp))

            Boton(
                Modifier.align(Alignment.CenterHorizontally)
                ,onClick)


            if (isValid && !dismissed){
                AddAlertDialog(dismiss)
            }
        }
    }
}

@Composable
fun TitleText(modifier: Modifier, contentAlignment: Alignment,text: String) {
    Box(modifier, contentAlignment = contentAlignment) {
        Text(
            text = text, color = Color.White,
            fontSize = 25.sp
        )
    }
}


@Composable
private fun CampoDeTexto(
    value: String
    ,isValid : Boolean
    ,x:(String) ->Unit
    ,modifier:Modifier
    ,placeHolder: String
    ,label: String
    ,transformation: VisualTransformation){
        Column(modifier=modifier) {
            Spacer(Modifier.size(5.dp))
            val col = if (isValid){Color.White} else {Color.Red}
            val tLabel = if (isValid)label else "ID OR PASSWORD INCORRECT"
            Text(text = tLabel ,color=col)
            OutlinedTextField(
                value = value,
                modifier = Modifier.width(300.dp),
                placeholder = { Text(text = placeHolder) },
                enabled = true,
                onValueChange = x,
                shape = RectangleShape,
                visualTransformation = transformation,
                colors =
                if (isValid){
                    OutlinedTextFieldDefaults.colors(
                        unfocusedTextColor = Color.LightGray,
                        focusedTextColor = Color.White,
                        unfocusedContainerColor = Color.Transparent,
                        focusedContainerColor = Color.Transparent,
                        focusedBorderColor = Color.White,
                        unfocusedBorderColor = Color.LightGray,
                        focusedPlaceholderColor = Color.LightGray,
                        unfocusedPlaceholderColor = Color.LightGray
                    )
                }else{
                    OutlinedTextFieldDefaults.colors(
                        unfocusedTextColor = Color.LightGray,
                        focusedTextColor = Color.White,
                        unfocusedContainerColor = Color.Transparent,
                        focusedContainerColor = Color.Transparent,
                        focusedBorderColor = Color.Red,
                        unfocusedBorderColor = Color.Red,
                        focusedLabelColor = Color.Red,
                        unfocusedLabelColor = Color.Red,
                        focusedPlaceholderColor = Color.LightGray,
                        unfocusedPlaceholderColor = Color.LightGray
                    )
                }
            )
        }
    }

@Composable
fun Boton(modifier: Modifier,onClick: () -> Unit) {
    OutlinedButton(
        colors = ButtonDefaults.buttonColors(
            containerColor = Color.Transparent
        )
        ,modifier = modifier
            .padding(10.dp)
            .width(300.dp)
        ,onClick = onClick
        ,shape = RectangleShape
        ,)
    {
        Text(text = "Sign in")
    }
}


@Composable
private fun AddAlertDialog(dismiss: () -> Unit) {
    AlertDialog(
        onDismissRequest = dismiss,
        title = { Text("Login") },
        text = { Text("Login correcto") },
        confirmButton = {
            Button(onClick = dismiss) {
                Text("OK")
            }
        }
    )
}