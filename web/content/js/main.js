/* 
 Created on : 17/08/2014, 09:24:58
 Author     : RENAN */

;$(function() {


    var $form = $('#login-form');
    var $user = $('#user');
    var $pass = $('#pass');
    var $info = $(".wf-login-info");
    var $gologin = $('#go-login'); 
    
    /* controle para fazer a caixa de login desaparecer
     * se o usuário clicar fora da area dela */
    $(document).on('click', function(e){
       if( !$gologin.has( e.target ).length ){
           $('.wf-login-box').fadeOut();
       }
    });
    

    /* botão de login */
    $('#btn-login').on('click', function(e) {

        var u = $user.val();
        var p = $pass.val();

        /* alert("USUÁRIO: " + u + "- SENHA: " + p); */
        if (!u.length > 0 || !p.length > 0) {
            e.preventDefault();
            if (u.length === 0)
                $user.addClass('wf-invalid');
            else
                $user.removeClass('wf-invalid');

            if (p.length === 0) {
                $pass.addClass('wf-invalid');
            } else {
                $pass.removeClass('wf-invalid');
            }

            $info.css('visibility', 'visible');
        } else {
            $form.submit();
        }
    });

    /* caixa de login */
    $('#wf-login-box').on('click', function(e) {
        e.preventDefault();
        if ($(window).width() <= 500) {
            window.open('login.html', "_self");
        } else {
            $('.wf-login-box').fadeIn();
        }
    });
    
    /* registro */
    $('#btn-register').on('click', function(e){
        e.preventDefault();
        
        var ok = false;
        var $form = $('#signup-form');
        var $el = $('.wf-signup-info');
        
        var username = $('#username').val();
        if( username.length > 0 ){
            $('#username').addClass('wf-valid');
            var $useremail = $('#useremail');
            if( $useremail.is(':valid') ){
                $('#useremail').addClass('wf-valid');
                var userpass = $('#userpass').val();
                var usercpass = $('#usercpass').val();
                if( userpass.length > 0 && usercpass.length > 0 && userpass === usercpass ){
                    $('#userpass').addClass('wf-valid');
                    $('#usercpass').addClass('wf-valid');
                    ok = true;
                }
            }
        }
        
        if( ok ){
            $form.submit();
        } else {
            if( $('#username').val().length <= 0 ){
                $('#username').removeClass('wf-valid');
                $('#username').addClass('wf-invalid');
            } else {
                $('#username').removeClass('wf-invalid' );
            }
            
            if( $('#userpass').val() !== $('#usercpass').val() || $('#usercpass').val().length <=0 || $('#usercpass').val().length <= 0 ){
                $('#usercpass').removeClass('wf-valid');
                $('#userpass').removeClass('wf-valid');
                $('#usercpass').addClass('wf-invalid');
                $('#userpass').addClass('wf-invalid');
            } else {
                $('#usercpass').removeClass('wf-invalid');
                $('#userpass').removeClass('wf-invalid');
            }
        }
    });

    /* controle caso o usuário redimensione a tela com a caixa de login aberta */
    $(window).resize(function() {
        if ($(window).width() < 500 && $('.wf-login-box').is(':visible')) {
            $('.wf-login-box').hide();
        }
    });
});