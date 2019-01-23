$(".dropdown-trigger").dropdown();

document.addEventListener('DOMContentLoaded', function() {
    var elems = document.querySelectorAll('.fixed-action-btn');
    var options = "";
    var instances = M.FloatingActionButton.init(elems, options);
  });


$(document).ready(function(){
	$("button[name=procurar]").click(function(){
		var cep = $("input[name=cep]").val().replace(/[^0-9]/, '');
		if(cep){
			var url = 'https://viacep.com.br/ws/'+cep+'/json';
			$.ajax({
        url: url,
        dataType: 'jsonp',
        crossDomain: true,
        contentType: "application/json",
				success : function(json){
				  if(json.logradouro){
					  $("input[name=logradouro]").val(json.logradouro);
					  $("input[name=logradouro]").focus();
						$("input[name=bairro]").val(json.bairro);
						$("input[name=bairro]").focus();
						$("input[name=cidade]").val(json.localidade);
						$("input[name=cidade]").focus()
						$("input[name=uf]").val(json.uf);
						$("input[name=uf]").focus();
					}
				}
			});
		}					
	});	
});

$(document).ready(function(){
    $('select').formSelect();
  }
);

$(document).ready(function(){
    $('.sidenav').sidenav();
  }
);