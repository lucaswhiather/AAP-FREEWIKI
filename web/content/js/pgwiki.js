var img = document.createElement('img');
img.setAttribute('class', 'loading');
img.setAttribute('alt', 'carregando');
img.setAttribute('width', '50');
img.setAttribute('height', '50');
img.setAttribute('src', 'content/img/carregando.gif');

$(document).ready(function() {
    carregarPgWiki('wikifree');

});

function carregarPgWiki(pgwiki) {

    $.ajax({
        type: 'POST',
        url: 'carregar.do', //Chama o servlet
        dataType: 'html', //retorna um html
        beforeSend: function() {
            document.getElementById('section').innerHTML = '';
            document.getElementById('section').appendChild(img);
        },
        data: {pgwiki: pgwiki},
        success: function(html) {
            $('section').css({height: "auto"});
            $('section').html(html);

            if (parseInt($('section').css("height")) > 500) {
                $('#corpo').css({height: 100 + parseInt($('section').css('height'))});
            }
        }
    });
}



