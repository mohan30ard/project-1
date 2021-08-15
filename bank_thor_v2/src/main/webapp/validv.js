form1.addEventListener('submit', function (e) {
    e.preventDefault()
    checkInputs()
    if (
        document.getElementById("errorFirstName").innerText === "" &&
        document.getElementById("errorLastName").innerText === "" &&
        document.getElementById("errorEmailId").innerText === "" &&
        document.getElementById("errorMobileNumber").innerText === "" &&
        document.getElementById("errorUserId").innerText === "" &&
        document.getElementById("errorPassword").innerText === ""
    ) {
        console.log("no errors");
        let url = "http://localhost:8080/bank_thor_v2/bank";
        fetch(url, {
            method: 'post',
            body: JSON.stringify({
                firstName: document.getElementById('firstname').value,
                lastName: document.getElementById('lastname').value,
                emailId: document.getElementById('emailid').value,
                mobileNumber: document.getElementById('mobilenumber').value,
                userId: document.getElementById('userid').value,
                password: document.getElementById('password').value
            }),
            headers: {
                'content-type': 'application/json; charset=UTF-8',
            }
        })//.then(response => response.json())
            // .then(data =>console.log(data))
            // .then((checkStatus))
            //     .then((response) => {
            //         document.getElementById("success-msg").innerHTML = "Message has been sent.";
            //         myForm.reset();
            //         return response.json();
            //     })
            //     .catch((error) => {
            //         document.getElementById("warning-msg").innerHTML = "Message not sent";
            //         for (errors in error) {
            //             myForm.elements[errorKey].classList.add('errorc');
            //         }

            //     })
            // .then(response =>response.json())
            // .then(res=>{
            //     let data = "<table class = 'table table-striped table-bordered'> <thead class='thead-dark'> <tr><th>UserId</th><th>First Name</th><th>Last Name</th><th>EmailId</th><th>Mobile Number</th></thead><tbody> "
            //     res.forEach(e => {
            //         data = data + "<tr><td>" + e.userId + "</td>";
            //         data = data + "<td>" + e.firstName + "</td>";
            //         data = data + "<td>" + e.lastName + "</td>";
            //         data = data + "<td>" + e.emailId + "</td>";
            //         data = data + "<td>" + e.mobileNumber + "</td></tr>";
            //     });
            //     data = data + "</tbody></table>"
            //     document.getElementById("results").innerHTML = data;

            // })
            .then(function (response) {
                alert("success");
                console.log("success");
                var results = document.getElementById('results')
                results.innerHTML = `<p>You have Succesfully Registered with Thor Bank V2.0 </p>
    <p><a href='/bank_thor_v2'>Click Here to LOGIN</a></p>`})
    } else {
        console.log("errors");
        var results = document.getElementById('results')
        results.innerHTML = `<p>Registration failed Please Try Again</p>`
    }


    function checkInputs() {

        const firstname = document.getElementById('firstname').value;
        const lastname = document.getElementById('lastname').value;
        const emailid = document.getElementById('emailid').value;
        const mobilenumber = document.getElementById('mobilenumber').value;
        const userid = document.getElementById("userid").value;
        const password = document.getElementById("password").value;

        let errorFirstName = "";
        let errorLastName = "";
        let errorEmailId = "";
        let errorMobileNumber = "";
        let errorUserId = "";
        let errorPassword = "";
        if (isValidFirstName(firstname)) {
            errorFirstName = "Firstname Cannot be Empty"
        } else {
            errorFirstName = "";
        }
        if (isValidLastName(lastname)) {
            errorLastName = "Lastname Cannot be Empty"
        } else {
            errorLastName = "";
        }
        if (isValidEmailId(emailid)) {
            errorEmailId = "Emailid Cannot be Empty"
        } else {
            errorEmailId = "";
        }
        if (isValidMobileNumber(mobilenumber)) {
            errorMobileNumber = "Mobilenumber Cannot be Empty"
        } else {
            errorMobileNumber = "";
        }
        if (isValidUserId(userid)) {
            errorUserId = "Username Cannot be Empty"
        } else {
            errorUserId = "";
        }
        if (isValidPassword(password)) {
            errorPassword = "Password Cannot be Empty"
        } else {
            errorPassword = "";
        }
        document.getElementById("errorFirstName").innerText = errorFirstName;
        document.getElementById("errorLastName").innerText = errorLastName;
        document.getElementById("errorEmailId").innerText = errorEmailId;
        document.getElementById("errorMobileNumber").innerText = errorMobileNumber;
        document.getElementById("errorUserId").innerText = errorUserId;
        document.getElementById("errorPassword").innerText = errorPassword;

        return (errorFirstName === 0 && errorLastName.length === 0 && errorEmailId.length === 0
            && errorMobileNumber === 0 && errorUserId.length === 0 && errorPassword.length === 0);
    }
    function isValidFirstName(firstname) {
        return firstname === "" || firstname.length < 5;
    }
    function isValidLastName(lastname) {
        return lastname === "" || lastname.length < 5;
    }
    function isValidEmailId(emailid) {
        return emailid === "" || emailid.length < 5;
    }
    function isValidMobileNumber(mobilenumber) {
        return mobilenumber === "" || mobilenumber.length < 10;
    }
    function isValidUserId(userid) {
        return userid === "" || userid.length < 5;
    }
    function isValidPassword(password) {
        return password === "" || password.length < 5;
    }

    // function checkStatus(response) {
    //     if (response.status >= 200 && response.status < 300) {
    //         return response
    //     } else if (response.status == 422) {
    //         document.getElementById("warning-msg").innerHTML = "Message not sent.";
    //     }
    //     else {
    //         var error = new Error(response.statusText)
    //         error.response = response
    //         throw error
    //     }
    // }

})


