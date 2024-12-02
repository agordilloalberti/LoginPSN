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
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.TextFieldColors
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

                    val id = "usuario@gmail.com"
                    val password = "1234"
                    var colors = OutlinedTextFieldDefaults.colors(
                        unfocusedTextColor = Color.LightGray,
                        focusedTextColor = Color.White,
                        unfocusedContainerColor = Color.Transparent,
                        focusedContainerColor = Color.Transparent,
                        focusedBorderColor = Color.White,
                        unfocusedBorderColor = Color.LightGray,
                        focusedPlaceholderColor = Color.LightGray,
                        unfocusedPlaceholderColor = Color.LightGray
                    )

                    val colorsDef = OutlinedTextFieldDefaults.colors(
                        unfocusedTextColor = Color.LightGray,
                        focusedTextColor = Color.White,
                        unfocusedContainerColor = Color.Transparent,
                        focusedContainerColor = Color.Transparent,
                        focusedBorderColor = Color.White,
                        unfocusedBorderColor = Color.LightGray,
                        focusedPlaceholderColor = Color.LightGray,
                        unfocusedPlaceholderColor = Color.LightGray
                    )
                    val colorsBad = OutlinedTextFieldDefaults.colors(
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


                    Login(
                        Modifier.padding(innerPadding)
                        ,textEmail
                        ,textPasswd
                        ,colors
                        ,{textPasswd = it}
                        ,{textEmail = it}
                        ,{
                            colors = if (textEmail==id && textPasswd==password){
                                colorsDef
                            }else{
                                colorsBad
                            }
                        })
                }
            }
        }
    }
}

@Composable
fun Login(
    modifier: Modifier
    , textEmail:String
    , textPasswd:String
    ,colors: TextFieldColors
    , changePasswd:(String) ->Unit
    , changeEmail:(String) ->Unit
    , onClick: () -> Unit
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
                .padding(20.dp,20.dp,0.dp,5.dp),
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
                .padding(0.dp,30.dp,0.dp,0.dp),
        ) {
            Row(modifier = Modifier.align(Alignment.CenterHorizontally)) {
                Image(
                    modifier = Modifier.size(65.dp, 55.dp)
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

            Text(modifier = Modifier.align(Alignment.CenterHorizontally)
                .padding(5.dp,30.dp,5.dp,15.dp),
                text = "Go online by signing in to PlayStation Network",
                color = Color.White,
                fontSize = 17.sp)

            CampoDeTexto(
                textEmail,
                colors,
                changeEmail,
                Modifier.align(Alignment.CenterHorizontally),
                stringResource(R.string.email),
                stringResource(R.string.sign_in_id_email_address),
                VisualTransformation.None
            )

            CampoDeTexto(
                textPasswd,
                colors,
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
    ,colors: TextFieldColors
    ,x:(String) ->Unit
    ,modifier:Modifier
    ,placeHolder: String
    ,label: String
    ,transformation: VisualTransformation){
        Column(modifier=modifier) {
            Spacer(Modifier.size(5.dp))
            Text(text = label,color=Color.White)
            OutlinedTextField(
                value = value,
                modifier = Modifier.width(300.dp),
                placeholder = { Text(text = placeHolder) },
                enabled = true,
                onValueChange = x,
                shape = RectangleShape,
                visualTransformation = transformation,
                colors = colors
            )
        }
    }

@Composable
fun Boton(modifier: Modifier,onClick: () -> Unit) {
    OutlinedButton(
        colors = ButtonDefaults.buttonColors(
            containerColor = Color.Transparent
        )
        ,modifier = modifier.padding(10.dp).width(300.dp)
        ,onClick = onClick
        ,shape = RectangleShape
        ,)
    {
        Text(text = "Sign in")
    }
}