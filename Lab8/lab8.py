"""
Andrew France
Dr. Parsons
SDEV 300
Lab 8

This program uses the flask library and html/css files to create a website that
is hosted on the user's local server.
"""
import fileinput
import string
import socket
from datetime import datetime
from flask import Flask, render_template, request, flash
from passlib.hash import sha256_crypt


app = Flask(__name__)
app.secret_key = 'secret_key'


@app.route('/')
def home():
    """Landing page. Home page of website and provides brief 'about'"""
    return render_template(
        'Home.html',
        title="Home",
        description="About: This website is dedicated to the U.S. Army Airborne and their "
                    "accomplishments during WWII."
    )


@app.route('/Login', methods=['GET', 'POST'])
def login():
    """
    Login page, takes entered username and password and checks if there is a match in database.
    Logs failed attempts to file.
    """
    if request.method == 'POST':
        username = request.form['username']
        password = request.form['password']
        login_success = False      # Bool to denote successful login
        with open('passfile.txt', 'r', encoding="utf-8") as file:
            # go through text file, split username and password from each line
            for i in file:
                user_f, pass_f = i.split(", ")
                pass_f = pass_f.strip()
                # if username and hashed password is verified, flash success
                if user_f == username and sha256_crypt.verify(password, pass_f):
                    flash('Login successful.')
                    login_success = True
            # Call logger, *** New for lab 8 ***
            logger(login_success)

    return render_template('Login.html')


# *** NEW FOR LAB 8 ***
@app.route('/UpdatePW', methods=['GET', 'POST'])
def update():
    """
    PW update form. Allows user to reset password after successful login
    """
    # I'm not sure how to configure flask properly to use authentication from login page
    # so pieces of login page were reused here for use in updating pw
    if request.method == 'POST':
        username = request.form['username']
        password = request.form['password']
        password_new = request.form['password_new']
        login_success = False  # Bool to denote successful login

        # Test new password against common list
        if pw_list(password_new):
            flash("New password is known as weak, enter a different password.")
        # Test new password meets original criteria
        elif not check_pw(password_new):
            flash('Password must contain at least 12 characters, one uppercase letter,'
                  ' one lowercase letter, one number, and one special character.')
        # else new password is good, update
        else:
            # hash new pw
            hash_pass = sha256_crypt.hash(password_new)
            # open pw file with fileinput to overwrite file with any updates
            with fileinput.FileInput('passfile.txt', inplace=True, backup='.bak') as f_new:
                # go through text file, split username and password from each line
                for i in f_new:
                    user_f, pass_f = i.split(", ")
                    pass_f = pass_f.strip()
                    # if username and hashed password is verified match,
                    # overwrite current line with new password
                    if user_f == username and sha256_crypt.verify(password, pass_f):
                        flash('Update successful.')
                        login_success = True
                        # end = "" to prevent blank lines in file
                        print(user_f + ', ' + hash_pass)
                    else:
                        # Else re-write current line as is to maintain original user/pass
                        print(user_f + ", " + pass_f)
            # call logger
            logger(login_success)

    return render_template('UpdatePW.html')


@app.route('/Register', methods=['GET', 'POST'])
def register():
    """
    Register page. Takes username and password, checks if valid, then adds to database(file)
    """
    # post user entries in registration form
    if request.method == 'POST':
        username = request.form['username']
        password = request.form['password']

        # Tests before confirming registration
        # Make sure username field isn't blank
        if not username:
            error = 'Please enter your username.'
            flash(error)
        # Make sure password field isn't blank
        elif not password:
            error = 'Please enter your password.'
            flash(error)
        elif any(c in string.punctuation for c in username):
            error = 'Username cannot contain any special characters.'
            flash(error)
        # Make sure username isn't repeat
        elif not check_reg(username):
            error = 'Username already taken.'
            flash(error)
        # Make sure password meets criteria
        elif not check_pw(password):
            error = ('Password must contain at least 12 characters, one uppercase letter,'
                     ' one lowercase letter, one number, and one special character.')
            flash(error)
        else:
            # encrypt password
            hash_pass = sha256_crypt.hash(password)
            # Save user entries onto text file, csv style
            with open('passfile.txt', "a", encoding="utf-8") as file:
                file.writelines(username + ', ' + hash_pass + '\n')
            flash('Registration successful.')

    return render_template('Register.html')


# *** NEW FOR LAB 8 ***
def logger(login_success):
    """
    This function creates a log of failed login attempts with date/time and IP
    """
    # Get IP address
    hostname = socket.gethostname()
    ip_address = socket.gethostbyname(hostname)
    # Get date/time
    now = datetime.now()
    date = now.strftime("%d/%m/%Y, %H:%M:%S")
    # if login failed, write date/IP to logger.txt
    if not login_success:
        flash('Username or password not found.')
        with open('logger.txt', 'a', encoding='utf-8') as log:
            log.writelines('Failed login attempt at: ' + date +
                           ' IP address: ' + ip_address + '\n')


# ***NEW FOR LAB 8 ***
def pw_list(password_new):
    """
    This function checks new password against a list of known passwords
    Returns true if found, false if not found
    """
    # open common passwords file provided
    with open('CommonPassword.txt', 'r', encoding='utf-8') as file:
        lines = file.readlines()
        for i in lines:
            if password_new in i:
                return True

    return False


def check_pw(password):
    """
    This function checks user entered password meets all criteria
    """
    # Check password for length at least 12
    if len(password) < 12:
        return False
    # check lowercase
    if not any(c in string.ascii_lowercase for c in password):
        return False
    # check uppercase
    if not any(c in string.ascii_uppercase for c in password):
        return False
    # check numbers
    if not any(c in string.digits for c in password):
        return False
    # check special characters
    if not any(c in string.punctuation for c in password):
        return False
    # if all conditions are passed
    return True


def check_reg(username):
    """
    This function checks that the username isn't already in the database(textfile)
    """
    # open file in read mode
    with open('passfile.txt', 'r', encoding="utf-8") as file:
        # list variable to store usernames in
        user_list = []
        # loop through list, separate the username from the password
        # on each line by splitting them at the comma ', '
        for i in file:
            user_file, pass_file = i.split(", ")
            # store username in list
            user_list.append(user_file)
    # if username is in file return false
    if username in user_list:
        return False
    # otherwise return true
    return True


@app.route('/Timeline')
def timeline():
    """Timeline page. Calls template for a table of WWII combat jumps."""
    return render_template(
        'Timeline.html',
        title="Timeline",
        description="This page shows a table of combat jumps during WWII. "
                    "Data was taken from globalsecurity.org. "
                    "Please visit the References page for more information."
    )


@app.route('/Battles')
def battles():
    """Battles page. Provides a brief description of notable battles from WWII airborne ops"""
    return render_template(
        'Battles.html',
        title="Notable Battles",
        description="Important combat jumps during WWII."
    )


@app.route('/References')
def references():
    """References pages. Provides links to external websites"""
    return render_template(
        'References.html',
        title="References",
        description="Please visit the following websites for more information."
    )


@app.route('/DateTime')
def date_time():
    """Date/time page. Uses python datetime functions to display current date/time on webpage"""
    now = datetime.now()
    date = now.strftime("%d/%m/%Y, %H:%M:%S")
    return render_template(
        'Date.html',
        title="Date/Time",
        date_time=date
    )


# Launch the website with debugger active
app.run(debug=True)
