form3.addEventListener('submit', function (e) {
    e.preventDefault()
    checkInputs()
    if (
        document.getElementById("errorAccountNumber").innerText === "" &&
        document.getElementById("errorAmount").innerText === ""
        //document.getElementById("errorOpeningBalance").innerText === ""
    ) {
        console.log("no errors");
        let url = "http://localhost:8080/bank_thor_v2/deposit";
        fetch(url, {
            method: 'post',
            body: JSON.stringify({
                accountNumber: document.getElementById('accountnumber').value,
                amount: document.getElementById("amount").value,

                // openingBalance: document.getElementById("openingbalance").value,
                // userId1: document.getElementById('userid').value
            }),
            headers: {
                'content-type': 'application/json; charset=UTF-8',
            }
        })
            .then(function (response) {
                alert("Amount Deposited successfully");
                console.log("success");
                var results = document.getElementById('results')
                results.innerHTML = `<p>You have Successfully Deposited to Account using Thor Bank V2.0 Services</p><br>
              <p><a href='/bank_thor_v2/successt'>Click Here to View Your Transaction Details</a></p><br>
    <p><a href='/bank_thor_v2/index.html'>Click Here to go to MainMenu</a></p><br>
    `})
    } else {
        console.log("errors");
        var results = document.getElementById('results')
        results.innerHTML = `<p>Deposit failed Please Try Again</p>`
    }


    function checkInputs() {

        const accountnumber = document.getElementById('accountnumber').value;
        const amount = document.getElementById('amount').value;
        // const userid = document.getElementById("userid").value;
        // const openingbalance = document.getElementById("openingbalance").value;

        let errorAccountNumber = "";
        let errorAmount = "";
        // let errorUserId = "";
        // let errorOpeningBalance = "";
        if (isValidAccountNumber(accountnumber)) {
            errorAccountNumber = "Account Number Cannot be Empty"
        } else {
            errorAccountNumber = "";
        }
        if (isValidAmount(amount)) {
            errorAmount = "Amount Cannot be Empty"
        }
        else {
            errorAmount = "";
        }
        // if (isValidUserId(userid)) {
        //     errorUserId = "Username Cannot be Empty"
        // } else {
        //     errorUserId = "";
        // }
        // if (isValidOpeningBalance(openingbalance)) {
        //     errorOpeningBalance = "OpeningBalance Cannot be Zero"
        // } else {
        //     errorOpeningBalance = "";
        // }
        document.getElementById("errorAccountNumber").innerText = errorAccountNumber;
        document.getElementById("errorAmount").innerText = errorAmount;
        // document.getElementById("errorUserId").innerText = errorUserId;
        // document.getElementById("errorOpeningBalance").innerText = errorOpeningBalance;

        return (errorAccountNumber.length === 0 && errorAmount.length === 0);
    }
    function isValidAccountNumber(accountNumber) {
        return accountNumber === "" || accountNumber.length < 11;
    }
    function isValidAmount(amount) {
        return amount === "" || amount.length < 2;
    }
    // function isValidOpeningBalance(openingbalance) {
    //     return openingbalance === "";
    // }
})


