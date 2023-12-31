
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<!DOCTYPE html>
<html>
    <head>
        <title>Login Page</title>
        <link href="https://fonts.googleapis.com/css?family=Poppins:200i,300,300i,400,400i,500,500i,600,600i,700,700i,800,800i,900,900i&display=swap" rel="stylesheet">
        <link rel="stylesheet" href="CSS+JS/css/bootstrap.min.css">
        <link rel="stylesheet" href="CSS+JS/css/nice-select.css">
        <link rel="stylesheet" href="CSS+JS/css/font-awesome.min.css">
        <link rel="stylesheet" href="CSS+JS/css/icofont.css">
        <link rel="stylesheet" href="CSS+JS/css/slicknav.min.css">
        <link rel="stylesheet" href="CSS+JS/css/owl-carousel.css">
        <link rel="stylesheet" href="CSS+JS/css/datepicker.css">
        <link rel="stylesheet" href="CSS+JS/css/animate.min.css">
        <link rel="stylesheet" href="CSS+JS/css/magnific-popup.css">      
        <link rel="stylesheet" href="CSS+JS/css/normalize.css">
        <link rel="stylesheet" href="CSS+JS/css/style.css">
        <link rel="stylesheet" href="CSS+JS/css/responsive.css">        
        <link rel="stylesheet" href="CSS+JS/css/customcss.css">

    </head>
    <%@include file="header.jsp" %>   

    <div class="col-3" style="margin: 0 auto; padding-top: 50px; background-color: #f5f5f5; border: 1px solid #ccc; border-radius: 10px;margin-top: 55px">
        <div class="div-center">
            <div class="content">
                <h3>Login</h3>
                <hr />
                <form action="" method="post">
                    <div class="form-group">
                        <label for="emailOrMobile">Email or Mobile</label>
                        <input type="text" name="emailOrMobile" class="form-control" id="exampleInputEmail1" placeholder="Email or Mobile">
                    </div>
                    <div class="form-group">
                        <label for="password">Password</label>
                        <input type="password" name="password" class="form-control" id="exampleInputPassword1" placeholder="Password">
                    </div>
                    <button type="submit" class="btn btn-primary" value="Login">Login</button>
                    <br>
                    <!-- Create a Google Sign-In button -->
                    <div class="g-signin2" data-onsuccess="onSignIn"></div>
                    <hr />
                    <button type="button" class="btn btn-link" id="signupButton" style="background-color: #FF7F50;">Signup</button>
                    <hr>
                    <button type="button" class="btn btn-link" id="resetPasswordButton" style="background-color: #50BF44;">Reset Password</button>
                    <br>
                    <script src="https://apis.google.com/js/platform.js" async defer></script>
                    <!-- Configure Google Sign-In -->
                    <meta name="google-signin-scope" content="profile email">
                    <meta name="google-signin-client_id" content="817724479118-go7u28tnlmhl67vuuc602ma8oerp87o9.apps.googleusercontent.com">

                    <!-- Implement the onSignIn function to handle Google login -->
                    <script>
                        function onSignIn(googleUser) {
                            // Get the user's profile information
                            var profile = googleUser.getBasicProfile();
                            // Send the user's email to your server for validation
                            var email = profile.getEmail();
                            // Validate the email domain
                            if (isEmailFromPermittedDomain(email)) {
                                // Email domain is permitted, implement logic to log in with the email provided by Google
                                // ...
                                // Redirect or perform other actions based on successful login
                            } else {
                                // Email domain is not permitted, prevent login or display an error message
                                // ...
                            }
                        }
                    </script>
                    <script>
                        // Get the "Signup" button element by its ID
                        const signupButton = document.getElementById('signupButton');
                        // Add a click event listener to navigate to /sign-up when the button is clicked
                        signupButton.addEventListener('click', function () {
                            window.location.href = 'register'; // Redirect to /sign-up
                        });
                        // Get the "Reset Password" button element by its ID
                        const resetPasswordButton = document.getElementById('resetPasswordButton');
                        // Add a click event listener to navigate to /change-password when the button is clicked
                        resetPasswordButton.addEventListener('click', function () {
                            window.location.href = 'change-password'; // Redirect to /change-password
                        });
                    </script>
                </form>
            </div>
        </div>
    </div>
    <%@include file="footer.jsp" %>

</body>
</html>

