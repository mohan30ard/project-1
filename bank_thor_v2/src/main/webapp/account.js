form2.addEventListener('submit', function (e) {
    e.preventDefault()
    checkInputs()
    if (
        document.getElementById("errorName").innerText === "" &&
        document.getElementById("errorPanCard").innerText === "" &&
        document.getElementById("errorUserId").innerText === "" &&
        document.getElementById("errorOpeningBalance").innerText === ""
    ) {
        console.log("no errors");
        let url = "http://localhost:8080/bank_thor_v2/create";
        fetch(url, {
            method: 'post',
            body: JSON.stringify({
                name: document.getElementById('name').value,
                panCard: document.getElementById("pancard").value,

                openingBalance: document.getElementById("openingbalance").value,
                userId1: document.getElementById('userid').value
            }),
            headers: {
                'content-type': 'application/json; charset=UTF-8',
            }
        })
            .then(function (response) {
                alert("Account Created successully");
                console.log("success");
                var results = document.getElementById('results')
                results.innerHTML = `<p>You have Succesfully Created an Account with Thor Bank V2.0 </p><br>
              <p><a href='/bank_thor_v2/successa'>Click Here to View Your Account Number</a></p><br>
    <p><a href='/bank_thor_v2/index.html'>Click Here to go to MainMenu</a></p><br>
    `})
    } else {
        console.log("errors");
        var results = document.getElementById('results')
        results.innerHTML = `<p>Account Opening failed Please Try Again</p>`
    }


    function checkInputs() {

        const name = document.getElementById('name').value;
        const pancard = document.getElementById('pancard').value;
        const userid = document.getElementById("userid").value;
        const openingbalance = document.getElementById("openingbalance").value;

        let errorName = "";
        let errorPanCard = "";
        let errorUserId = "";
        let errorOpeningBalance = "";
        if (isValidName(name)) {
            errorName = "name Cannot be Empty"
        } else {
            errorName = "";
        }
        if (isValidPanCard(pancard)) {
            errorPanCard = "PanCard Cannot be Empty"
        }
        else {
            errorPanCard = "";
        }
        if (isValidUserId(userid)) {
            errorUserId = "Username Cannot be Empty"
        } else {
            errorUserId = "";
        }
        if (isValidOpeningBalance(openingbalance)) {
            errorOpeningBalance = "OpeningBalance Cannot be Zero"
        } else {
            errorOpeningBalance = "";
        }
        document.getElementById("errorName").innerText = errorName;
        document.getElementById("errorPanCard").innerText = errorPanCard;
        document.getElementById("errorUserId").innerText = errorUserId;
        document.getElementById("errorOpeningBalance").innerText = errorOpeningBalance;

        return (errorName === 0 && errorPanCard === 0 && errorUserId.length === 0 && errorOpeningBalance.length === 0);
    }
    function isValidName(name) {
        return name === "" || name.length < 5;
    }
    function isValidPanCard(pancard) {
        return pancard === "" || pancard.length < 5;
    }
    function isValidUserId(userid) {
        return userid === "" || userid.length < 5;
    }
    function isValidOpeningBalance(openingbalance) {
        return openingbalance === "";
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

// document.getElementById("create").onclick = function () {
//     location.href = "http://localhost:8080/bank_thor_v2/successr";
// };
