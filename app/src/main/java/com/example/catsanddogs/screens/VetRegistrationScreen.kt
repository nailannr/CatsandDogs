package com.example.catsanddogs.screens

import android.content.Context
import android.net.Uri
import android.util.Log
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Add
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonColors
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.catsanddogs.data.SignupViewModel
import com.example.catsanddogs.data.SignupUIEvent
import com.example.catsanddogs.ui.theme.textFieldContainer
import com.google.firebase.Firebase
import com.google.firebase.firestore.firestore
import com.google.firebase.storage.StorageReference
import com.google.firebase.storage.storage
import java.util.UUID


@Composable
fun VetRegistrationScreen(navController: NavController,
                          onButtonClicked: () -> Unit,
                          signupViewModel: SignupViewModel = viewModel()) {
    var addImage by rememberSaveable {
        mutableStateOf("")
    }
    var uri by remember{
        mutableStateOf<Uri?>(null)
    }
    val singlePhotoPicker = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.PickVisualMedia(),
        onResult = {
            uri = it
        }
    )
    val context = LocalContext.current

    Box(modifier= Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ){
        Surface()
        {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center,
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color(0xFFFFDBE9))
                    .verticalScroll(
                        rememberScrollState()
                    )) {

                TopSection()

                Spacer(modifier = Modifier.height(15.dp))

                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(horizontal = 10.dp)
                ){
                    var fName= NormalTextField(name = "First Name", modifier = Modifier.fillMaxWidth(),
                        onTextSelected ={
                            signupViewModel.onEvent(SignupUIEvent.FirstNameChanged(it)) },
                        errorStatus = signupViewModel.registrationUIState.value.firstNameError)

                    var lName= NormalTextField(name = "Last Name", modifier = Modifier.fillMaxWidth(),
                        onTextSelected = {
                            signupViewModel.onEvent(SignupUIEvent.LastNameChanged(it)) },
                        errorStatus = signupViewModel.registrationUIState.value.lastNameError)

                    var email= NormalTextField(name = "Email", modifier = Modifier.fillMaxWidth(),
                        onTextSelected = {
                            signupViewModel.onEvent(SignupUIEvent.EmailChanged(it)) },
                        errorStatus = signupViewModel.registrationUIState.value.emailError)

                    var phn= NormalTextField(name = "Phone Number", modifier = Modifier.fillMaxWidth(),
                        onTextSelected = {
                            signupViewModel.onEvent(SignupUIEvent.PhoneNumChanged(it)) },
                        errorStatus = signupViewModel.registrationUIState.value.phoneNumError)

//                    var lis= NormalTextField(name = "License Number", modifier = Modifier.fillMaxWidth(),
//                        onTextSelected = {
//                            signupViewModel.onEvent(SignupUIEvent.LicenseChanged(it)) },
//                        errorStatus = signupViewModel.registrationUIState.value.licenseError)

                    NormalTextField(name = "Degree", modifier = Modifier.fillMaxWidth(),
                        onTextSelected ={
                                        signupViewModel.onEvent(SignupUIEvent.DegreeChanged(it))
                        } ,
                        errorStatus = signupViewModel.registrationUIState.value.degreeError)

                    NormalTextField(name = "Currently workin in", modifier = Modifier.fillMaxWidth(),
                        onTextSelected = {
                                         signupViewModel.onEvent(SignupUIEvent.WorkChanged(it))
                        },
                        errorStatus = signupViewModel.registrationUIState.value.workError)


                    // image textfield:
//                    var addImage by rememberSaveable {
//                        mutableStateOf("")
//                    }
//                    var uri by remember{
//                        mutableStateOf<Uri?>(null)
//                    }
//                    val singlePhotoPicker = rememberLauncherForActivityResult(
//                        contract = ActivityResultContracts.PickVisualMedia(),
//                        onResult = {
//                            uri = it
//                        }
//                    )
                    imageTextField(
                        modifier = Modifier.fillMaxWidth(),
                        name = "Add image",
                        onValueChange = {
                                        addImage = it
                        },
                        errorStatus = true,
                        iconButtonOnClick = {
                            singlePhotoPicker.launch(
                                PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly)
                            )
                        },
                        leadingImageVector = Icons.Outlined.Add,
                        trailingImageVector = null,
                        visibility = null
                    )

                    var sPass= PasswordTextField(name = "Set Password", modifier = Modifier.fillMaxWidth(),
                        onTextSelected = {
                            signupViewModel.onEvent(SignupUIEvent.SetPassChanged(it)) },
                        errorStatus = signupViewModel.registrationUIState.value.setPassError)

//                    var cPass= PasswordTextField(name = "Confirm Password", modifier = Modifier.fillMaxWidth(),
//                        onTextSelected = {
//                            signupViewModel.onEvent(SignupUIEvent.ConfirmPassChanged(it)) },
//                        errorStatus = signupViewModel.registrationUIState.value.confirmPassError)

                    Spacer(modifier = Modifier.height(20.dp))


                }
                buttonComponent(
                    onButtonClicked = {
                        uri?.let{
                            upload(uri=it,
                                context=context,
                                firstName = signupViewModel.registrationUIState.value.firstName,
                                lastName = signupViewModel.registrationUIState.value.lastName,
                                email = signupViewModel.registrationUIState.value.email,
                                contactNum = signupViewModel.registrationUIState.value.phoneNum,
                                degree = signupViewModel.registrationUIState.value.degree,
                                work = signupViewModel.registrationUIState.value.work)
                        }
                    },
                    isEnabled = signupViewModel.allValidationPassedV.value)
            }
        }
        if (signupViewModel.signUpInProgress.value) {
            CircularProgressIndicator()
        }
    }

}


fun upload(
    uri: Uri, context: Context, firstName: String, lastName: String, email: String,
    contactNum: String, degree: String, work: String

){
    val unique_image_name = UUID.randomUUID()
    val newVet = hashMapOf(
        "firstName" to "$firstName",
        "lastName" to "$lastName",
        "eMail" to "$email",
        "contactNum" to "$contactNum",
        "dEgree" to "$degree",
        "wOrk" to "$work",
        "iMage" to "https://firebasestorage.googleapis.com/v0/b/cats-and-dogs-792fd.appspot.com/o/vet%2Ffemaledoc.png?alt=media&token=e6a83ad2-fcc3-48b4-beff-488ab089701b"

    )

    val storage= Firebase.storage
    val db = Firebase.firestore

    db.collection("vetInfo")
        .add(newVet)
        .addOnSuccessListener { documentReference ->
            Log.d("newPetAdded", "DocumentSnapshot added with ID: ${documentReference.id}")
        }
        .addOnFailureListener { e ->
            Log.w("failedHouseAdded", "Error adding document", e)
        }

    var storageRef = storage.reference
    var spaceRef: StorageReference


    spaceRef = storageRef.child("vet/${unique_image_name}.jpg")

    val byteArray: ByteArray? = context.contentResolver
        .openInputStream(uri)
        ?.use { it.readBytes() }

    byteArray?.let{

        var uploadTask = spaceRef.putBytes(byteArray)
        uploadTask.addOnFailureListener {
            Toast.makeText(
                context,
                "upload failed",
                Toast.LENGTH_SHORT
            ).show()
            // Handle unsuccessful uploads
        }.addOnSuccessListener { taskSnapshot ->
            // taskSnapshot.metadata contains file metadata such as size, content-type, etc.
            // ...
            Toast.makeText(
                context,
                "upload successful!",
                Toast.LENGTH_SHORT
            ).show()
        }
    }


}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun imageTextField(
    modifier: Modifier = Modifier,
    name: String,
    onValueChange: (String) -> Unit,
    errorStatus: Boolean,
    iconButtonOnClick: () -> Unit,
    leadingImageVector: ImageVector?,
    trailingImageVector: ImageVector?,
    visibility: Boolean?,
){
    var text by remember { mutableStateOf("") }
    Text(
        text = name ,
        fontSize = 20.sp,
        fontWeight = FontWeight.Bold,
        textAlign = TextAlign.Left
    )
    
    TextField(
        modifier = Modifier.fillMaxWidth(),
        value = text,
        onValueChange = {
            text = it
            onValueChange(it)
        },
        colors = TextFieldDefaults.textFieldColors(
            containerColor = MaterialTheme.colorScheme.textFieldContainer

        ),
        leadingIcon = {
            if (leadingImageVector != null) {
                IconButton(onClick = iconButtonOnClick){
                    Icon(
                        imageVector = leadingImageVector,
                        contentDescription = null
                    )
                }
            }
        },
        trailingIcon = {
            IconButton(onClick = iconButtonOnClick) {
                if (trailingImageVector != null) {
                    Icon(
                        imageVector = trailingImageVector,
                        contentDescription = null
                    )
                }
            }
        },
        isError = !errorStatus)

}



@Composable
private fun buttonComponent(
                            onButtonClicked: ()-> Unit,
                            isEnabled: Boolean){

    Button(
        modifier = Modifier
            .width(150.dp)
            .height(50.dp),
        onClick = {
            onButtonClicked.invoke()
        },
        colors = ButtonDefaults.buttonColors(
            Color(218, 102, 147, 255),
            contentColor = Color.White
        ),
        shape = RoundedCornerShape(size = 4.dp),
        enabled = isEnabled

    ){
        Text(
            text = "Register!",
            style = MaterialTheme.typography.labelMedium.copy(fontWeight = FontWeight.Bold)
        )
    }


}


@Composable
private fun TopSection(){


    Box (modifier = Modifier.padding(0.dp, 50.dp, 0.dp, 0.dp)){
        Text(
            text = "Register as Veterinarian",
            fontSize = 26.sp,
            fontWeight = FontWeight.Bold,
            textDecoration = TextDecoration.Underline,
            lineHeight = 50.sp,
            textAlign = TextAlign.Center


        )
    }
}