A CAPTCHA is a program that can generate and grade tests that humans can pass but computer programs ‘cannot‘. One of strategies followed are showing an image to user with distorted text, and user should write text in input area. If showed text is the same as input by user, then we can ‘assure‘ that a human is on computer.

Captchas have several applications for practical security, for example:
Preventing Spam in comment fields.
Protecting from Massive User Registration.
Preventing Dictionary Attacks.
…
These distorted texts are acquired as follows:
1. Digitizing physical books/newspaper.
2. Pages are photographically scanned, and then transformed into text using ‘Optical Character Recognition‘ (OCR).
3. OCR is not perfect, each word that cannot be read correctly by OCR is placed on an image and used as a CAPTCHA.
4. Word that cannot be read correctly by OCR is given to a user with another word for which the answer is already known. Then is asked to read both words, if user solves the one for which the answer is known, the system assumes their answer is correct for the new one. The system then gives the new image to a number of other people to determine, with higher confidence, whether the original answer was correct.


Now you know how captcha works, the problem is that if you want to use captchas in your website, you should implement yourself process described above, and of course this is not easy and tedious work is required digitalizing works. For this reason there are some ‘captcha providers‘ that have done this work for us. One of these providers is reCaptcha http://www.google.com/recaptcha. reCaptcha is a free captcha service that provides us these captchas ready to be used in our site. As developers we only have to embedded a piece of code in client side for showing captcha image and text area, and in server side, calling a function for resolving input data. reCaptcha provides plugins for dealing with lot of programming languages like Java, PHP, Perl, …
This post will guide you on how to use reCaptcha in Spring MVC web application. The application consists in a form to register a new user. This form contains a captcha for avoiding a bot starts a massive registration attack.
First step is open an account to reCaptcha site (you can use yourgoogle account or create a new one).
Once you have entered go to My Account – Add New Site.
Then at domain box you should write the domain which will contain captcha validation. For this example I have entered localhost and I have checked Enable this key on all domains (global key). Of course information provided here is for testing porpoise and in production environment should be different. After you have registered your site, two keys are provided, private key (XXXX) and a public key (YYYY).

Before coding, let me show basic life-cycle of a reCAPTCHA challenge. Diagram is from reCaptcha web:

Second step is create a Spring MVC application, no secret here, I am going to explain only parts that are implied in reCaptcha integration. Apart from SpringMVC dependencies, recaptcha4j API should be added:

<dependency>
 	  <groupId>net.tanesha.recaptcha4j<groupId>
      <artifactId>recaptcha4j<artifactId>
      <version>0.0.7<version>
<dependency>


recaptcha4j.jar is an API that provides a simple way to place a captcha on your Java-based website. The library wraps the reCAPTCHA API.
Integrating reCaptcha into a form, requires two modifications:
One in client side, for connecting to reCaptcha server and get the challenge.
Second one in server-side for connecting to reCaptcha server to send the user’s answer, and give back a response.

Client side:
-------------
For client side a tagfile has been created to encapsulate all logic of reCaptcha API in a single point, so can be reused in all JSP forms.

<%@ tag import='net.tanesha.recaptcha.ReCaptcha' %>
<%@ tag import='net.tanesha.recaptcha.ReCaptchaFactory' %>
<%@ attribute name='privateKey' required='true' rtexprvalue='false' %>
<%@ attribute name='publicKey' required='true' rtexprvalue='false' %>

<%
 ReCaptcha c = ReCaptchaFactory.newReCaptcha(publicKey, privateKey, false);
 out.print(c.createRecaptchaHtml(null, null));
%>

reCaptcha class requires the private key (XXXX) and the public key (YYYY) provided by reCaptcha in step one. The method createRecaptchaHtml(…) creates a piece of html code to show the challenge. In fact it generates something like:

App URL: http://localhost:8080/reCaptchaSpringMVC/