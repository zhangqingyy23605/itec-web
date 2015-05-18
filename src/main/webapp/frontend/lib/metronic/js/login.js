var Login = function () {
    return {
        //main function to initiate the module
        init: function () {
            $('.login-form').validate({
                errorElement: 'label', //default input error message container
                errorClass: 'help-inline', // default input error message class
                focusInvalid: false, // do not focus the last invalid input
                rules: {
                    username: {
                        required: true
                    },
                    password: {
                        required: true
                    },
                    remember: {
                        required: false
                    }
                },
                messages: {
                    username: {
                        required: "请输入用户名"
                    },
                    password: {
                        required: "请输入密码"
                    }
                },
                invalidHandler: function (event, validator) { //display error alert on form submit
                    //$('.alert-error', $('.login-form')).show();
                },
                highlight: function (element) { // hightlight error inputs
                    $(element)
                        .closest('.control-group').addClass('error'); // set error class to the control group
                },
                success: function (label) {
                    label.closest('.control-group').removeClass('error');
                    label.remove();
                },
                errorPlacement: function (error, element) {
                    error.addClass('help-small no-left-padding').insertAfter(element.closest('.input-icon'));
                },
                submitHandler: function (form) {
                    form.submit();
                }
            });
            $('.login-form input').keypress(function (e) {
                if (e.which == 13) {
                    $('.login-form').validate().form().submit();
                    return false;
                }
            });

            $('.forget-form').validate({
                errorElement: 'label', //default input error message container
                errorClass: 'help-inline', // default input error message class
                focusInvalid: false, // do not focus the last invalid input
                ignore: "",
                rules: {
                    email: {
                        required: true,
                        email: true
                    }
                },
                messages: {
                    email: {
                        required: "请输入电子邮箱"
                    }
                },
                invalidHandler: function (event, validator) { //display error alert on form submit
                },
                highlight: function (element) { // hightlight error inputs
                    $(element)
                        .closest('.control-group').addClass('error'); // set error class to the control group
                },
                success: function (label) {
                    label.closest('.control-group').removeClass('error');
                    label.remove();
                },
                errorPlacement: function (error, element) {
                    error.addClass('help-small no-left-padding').insertAfter(element.closest('.input-icon'));
                },
                submitHandler: function (form) {
                    form().submit();
                }
            });
            $('.forget-form input').keypress(function (e) {
                if (e.which == 13) {
                    $('.register-form').validate().form().submit();
                    return false;
                }
            });
            $('#forget-password').click(function () {
                $('.login-form').hide();
                $('.forget-form').show();
            });
            $('#back-btn').click(function () {
                $('.login-form').show();
                $('.forget-form').hide();
            });

            $('.register-form').validate({
                errorElement: 'label', //default input error message container
                errorClass: 'help-inline', // default input error message class
                focusInvalid: false, // do not focus the last invalid input
                ignore: "",
                rules: {
                    username: {
                        required: true,
                        remote: {
                            url: "/auth/exists",
                            type: "get",
                            data: {
                                username: function() {
                                    return $(".register-form input[name='username']").val();
                                }
                            },
                        }
                    },
                    password: {
                        required: true
                    },
                    rpassword: {
                        equalTo: "#register_password"
                    },
                    email: {
                        required: true,
                        email: true
                    },
                    type: {
                        required: true
                    }
                },
                messages: { // custom messages for radio buttons and checkboxes
                    username: {
                        remote: $.validator.format("用户名{0}已经被使用")
                    },
                    type: {
                        required: "该字段不能为空"
                    }
                },
                invalidHandler: function (event, validator) { //display error alert on form submit

                },
                highlight: function (element) { // hightlight error inputs
                    $(element)
                        .closest('.control-group').addClass('error'); // set error class to the control group
                },
                success: function (label) {
                    label.closest('.control-group').removeClass('error');
                    label.remove();
                },
                errorPlacement: function (error, element) {
                    if (element.attr("name") == "type") { // insert checkbox errors after the container
                        error.addClass('help-small no-left-padding').insertAfter($('#form_2_type_error'));
                    } else {
                        error.addClass('help-small no-left-padding').insertAfter(element.closest('.input-icon'));
                    }
                },
                submitHandler: function (form) {
                    form().submit();
                }
            });
            $('.register-form input').keypress(function (e) {
                if (e.which == 13) {
                    $('.register-form').validate().form().submit();
                    return false;
                }
            });
            $('#register-btn').click(function () {
                $('.login-form').hide();
                $('.register-form').show();
            });
            $('#register-back-btn').click(function () {
                $('.login-form').show();
                $('.register-form').hide();
            });
        }

    };

}();