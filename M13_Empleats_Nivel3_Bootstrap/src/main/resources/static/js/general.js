
		
		var posOrdenar= 1;
		
		function escribir(idParam){
		
			var id = idParam.target.id;
			var idTxt = "";
			var separados ="";
			if(id.includes("txt")){
				separados = id.split("-");	
				idTxt = id;	
				id = separados[0];
					
			}else{
				idTxt = id + '-txt'
			}
						
			document.getElementById(id).style.opacity = 0;
			document.getElementById(idTxt).textContent=id + '  Esborrar';
		}
		
		function salir(idParam){
			var id = idParam.target.id;
			var idTxt = "";
			var separados ="";
			if(id.includes("txt")){
				separados = id.split("-");	
				idTxt = id;	
				id = separados[0];		
			}else{
				idTxt = id + '-txt'
			}
		
			document.getElementById(id).style.opacity = 1;
			document.getElementById(idTxt).textContent="X";
		}		
		
		// vacia la tabla
		// y dibuja la cabecera de la tabla Empleados
		function capTablaEmpHtml(){
			$('#tbLlistatEmp').empty();
			$('#tbLlistatEmp').append(
					$(								
						"<tr>" + 
							"<th>ID</th>" +
							"<th>NOM</th>" +
							"<th>FEINA</th>" +
							"<th>SALARI</th>" +
							"<th width='100'>ESBORRAR</th>" + 
							"<th width='112'>EDITAR</th>" +									
						"</tr>" 								
					)	
				);									
		}
		
		// dibuja el cuerpo de la tabla Empleados
		function cosTablaEmpHtml(valor){
			$('#tbLlistatEmp').append(
					$(								
							"<tr>" + 
							"<td>" + valor.idEmpleat + "</td>" +
							"<td>" + valor.nom + "</td>" +
							"<td>" + valor.feina + "</td>" +
							"<td>" + valor.salari + "</td>" +
							"<td width='100'>" +
								"<svg height='24' width='104' style='background-color:green'>" +
									"<circle id='" + valor.idEmpleat  + "' cx='12' cy='12' r='10' stroke='black' stroke-width='1' fill='red' " + 
									" onmouseOver='escribir(event)' onmouseout='salir(event)' />" +
									"<text id='" + valor.idEmpleat + "-txt' x='8' y='17'  style='font-family: Consolas' fill='cyan'" +
									"onmouseOver='escribir(event);' onmouseout='salir(event);'" +
									'onclick="borrarEmpleado(' + valor.idEmpleat + ');"' +											
									">X</text>" +
									
								"</svg>"	+	
							"</td>"	+	
							"<td>" + 
							"<a id=" + valor.idEmpleat + " width='112' href='#'> EDITAR [" + valor.idEmpleat + "]" +
							"</a>" +
							"</td>" +									
						"</tr>"								
					)	
				);
		}
		
		function borrarEmpleado(idEmpleado){
			window.location.href='http://localhost:8080/api/ajaxrest/delete/' + idEmpleado;
			
			$('#notes').text("Borrant l'ID: " + idEmpleado + "...");
			
			setTimeout(function(){	
				clickPorSeleccion();
				$('#notes').text("Últim registre borrat tenia l'ID: " + idEmpleado );
			},1000);			
		}
		

		
		function ordenarListadoEmpleado(orden){
			capTablaEmpHtml();
			
			$.ajax(
				{
					type : 'GET',
					url : '/api/ajaxrest/order/' + orden,
					dataType: "json"
				}
			).done(
					function (msg){
					msg.forEach(function(valor){
						$('#tbLlistatEmp').append(
							$(								
								cosTablaEmpHtml(valor)							
							)	
						);						
					});
					}
			).fail(function (jqXHR, textStatus) {
					alert(textStatus  + " http://localhost:8080/api/ajaxrest/   - dentro error");
				});						
		}

		// despues de crear o borrar un empleado ordena la tabla segun ya estaba ordenada
		function clickPorSeleccion(){
			switch(posOrdenar){
			
			case 1:
				$('#llistaEmpleat').click();
				console.log("entro por ID");
				break;
			case 2:
				$('#llistaPerNom').click();
				console.log("entro por nombre");
				break;	
			case 3:
				$('#llistaPerFeina').click();
				console.log("entro por feina");
				break;
			case 4:
				$('#llistaPerSalari').click();
				console.log("entro por salario");
				break;
			default:
				$('#llistaEmpleat').click();
			    console.log("entro por ID Default ");
				break;
			};			
		}
		
		
		// Gestion de errores
		function getErrores (jqXHR, textStatus, errorThrown, cami ){

			  if (jqXHR.status === 0) {
		
			    alert(cami + '\nNot connect: Verify Network.');
		
			  } else if (jqXHR.status == 404) {
		
			    alert(cami + '\nRequested page not found [404]');
		
			  } else if (jqXHR.status == 500) {
		
			    alert(cami + '\nInternal Server Error [500].');
		
			  } else if (textStatus === 'parsererror') {
		
			    alert(cami + '\nRequested JSON parse failed.');
		
			  } else if (textStatus === 'timeout') {
		
			    alert(cami + '\nTime out error.');
		
			  } else if (textStatus === 'abort') {
		
			    alert(cami + '\nAjax request aborted.');
		
			  } else {
		
			    alert(cami + '\nUncaught Error: ' + jqXHR.responseText);
		
			  }			
		}
		
		
		
		$(document).ready(function(){
			
			(function () {
				  'use strict'

				  // Fetch all the forms we want to apply custom Bootstrap validation styles to
				  var forms = document.querySelectorAll('.needs-validation')

				  // Loop over them and prevent submission
				  Array.prototype.slice.call(forms)
				    .forEach(function (form) {
				      form.addEventListener('submit', function (event) {
				        if (!form.checkValidity()) {
				          event.preventDefault()
				          event.stopPropagation()
				        }

				        form.classList.add('was-validated')
				      }, false)
				    })
				})()
			
			
			 var alturaPantalla = screen.height;
			 var alturaDivTabla = alturaPantalla -360;
			 console.log("Altura pantalla: " + alturaPantalla);
			 console.log("Altura div: " + alturaDivTabla);
			
			 $('#divTabla').height(alturaDivTabla+'px');			 
			
			 $('#formEmpl').on('submit', function(e){
				e.preventDefault();
				var id = $.trim($('#numId').text());
			  	var nom = $.trim($('#nom').val());
			  	//var fenia= $('#feines option:selected').text();
			  	var	feina = document.getElementById('feines').value;
			  	var sou = $('#s2').text();
			  	
			  	
				console.log(id);
				console.log(nom);
				console.log(feina);
				console.log(sou);
				
				console.log("-----");
				console.log(e);
				console.log("-----");
				
				var jsonForm = "";
				

				
				console.log(jsonForm);

				if(nom !=""){				
					if(id == "Automàtic"){
						// si el registre es NOU
						jsonForm = {
								'nom' : nom,
								'feina' : feina,
								'salari' : sou
							}
						
						$.ajax(
								{
									url :  '/api/ajaxrest/save',
									type : 'post',
									dataType: 'json',
									contentType: 'application/json',
									data: JSON.stringify(jsonForm),
									
								}
							).done(
									function (data){
										console.log(data);
	
										clickPorSeleccion();
																	
										$('#numId').text(" Automàtic");
										$('#nom').val("");
										$('#s2').text("1800");
										$('#feines option[value=' +  'CONDUCTOR' + ']').attr('selected',true);
										$('#feines option:selected').text('CONDUCTOR');
										$('#salarios option[value=' +  'CONDUCTOR'  + ']').attr('selected',true);	
										
										// llenando el campo información de la base de la pagina
										$('#notes').text("Nou registre ID: " +  data.idEmpleat);
										
										}
							).fail( function( jqXHR, textStatus, errorThrown ) {
						
								var cami = "No encuentro la URL-> http://loacalhost:8080/api/ajaxrest/save";
	
								getErrores(jqXHR, textStatus, errorThrown, cami);
							});
							
					
					}else{
						// Regisre a MODIFICAR	
						jsonForm = {
								'idEmpleat' : id,	
								'nom' : nom,
								'feina' : feina
							}
						$.ajax(
								{
									url :  '/api/ajaxrest/',
									type : 'put',
									dataType: 'json',
									contentType: 'application/json',
									data: JSON.stringify(jsonForm),
									
								}
							).done(
									function (data){
										console.log(data);
										$('#llistaEmpleat').click();
																	
										$('#numId').text(" Automàtic");
										$('#nom').val("");
										$('#s2').text("1800");
										$('#feines option[value=' +  'CONDUCTOR' + ']').attr('selected',true);
										$('#feines option:selected').text('CONDUCTOR');
										$('#salarios option[value=' +  'CONDUCTOR'  + ']').attr('selected',true);	
										
										// llenando el campo información de la base de la pagina
										$('#notes').text("Últim ID modificat: " + data.idEmpleat );
										
										}
							).fail(function (jqXHR, textStatus) {
									var cami = "No encuentro la URL-> http://loacalhost:8080/api/ajaxrest/";
									getErrores(jqXHR, textStatus, errorThrown, cami);
								});	
						
						
					}
				}
			 });
		
			
			//menu desplegable feina
			$('#feines').on('click', function(e){
				feina = $('option:selected', this).attr('value');
				indice = $('#feines').prop('selectedIndex')+1;				
				sou = $('#salarios :nth-child(' + indice + ')').prop('selected',true).attr('value');
				$('#s2').text(sou);						
			});
			
			
			//autocarga de los menus desplegables de feines
			$.ajax(
					{
						type : 'GET',
						url : '/api/ajaxrest/feines',
						dataType: "json"
					}
				).done(
						function (msg){
							$('#s2').text(msg[0].salari);	
							$('#feines').empty();
							$('#salarios').empty();
							msg.forEach(function(feina){
								$('#feines').append(
									$(		
										"<option value='" + feina.nomFeina + "'>" + feina.nomFeina  + "</option>"							
									)	
								);	
								$('#salarios').append(
										$(		
											"<option value='" + feina.salari + "'>" + feina.salari  + "</option>"							
										)	
									);
							});
							}
				).fail(function (jqXHR, textStatus) {
						var cami = textStatus +  url + " dentro error";
						getErrores(jqXHR, textStatus, errorThrown, cami);
					});

			
			// al lincar en un link <a> en esta campo <div>
			$('#divTabla').on('click','a', function(e){
				// var $form =  $('#formEmpl');
				var idHidde = $(this).attr('id');
				$('#result1').empty();
					$.ajax({
						type: 'GET',
						url: '/api/ajaxrest/' + idHidde ,
						dataType: 'json',
						contextType: 'application/json',
						success: function(result) {
							
						//	$('#salarios :nth-child(' + indice + ')').prop('selected',true);
							
							$('#numId').text(" " + result.idEmpleat);
							$('#nom').val(result.nom);
							$('#s2').text(result.salari);
							$('#feines option[value=' +  result.feina  + ']').attr('selected',true);
							$('#feines option:selected').text(result.feina);
							$('#salarios option[value=' +  result.salari  + ']').attr('selected',true);	
							
							$('#salarios option:selected').text(result.salari);
							
							// relleno del campo informacion en la base de la pagina
							$('#notes').text("Modificant l'ID: " + result.idEmpleat);
							
						}				
					});
				
				
				
			//	e.stopPropagation();
			//	e.preventDefalult();
			})

				
			$('#reset').click(function(){
				var idHidde = '2'
				$.ajax({
					type: 'GET',
					url: '/api/ajaxrest/' + idHidde ,
					dataType: 'json',
					contextType: 'application/json',
					success: function(result) {

						$('#numId').empty();
						$('#numId').text(" Automàtic")
						$('#nom').val("");
				//		$('#feines option[value=' +  result.feina  + ']').attr('selected',true);
				//		$('#salarios option[value=' +  result.salari  + ']').attr('selected',true);
						
						 $('#feines :nth-child(1)').prop('selected',true);
						var sou = $('#salarios :nth-child(1)').prop('selected',true).attr('value');
						
						$('#s2').empty();
						$('#s2').text(sou);
						$('#notes').text("Formulari net");
					}				
				});
			});
			
			// Botones de ordenación
			$('#llistaEmpleat').on('click',function(){			
				ordenarListadoEmpleado('ID');
				$('#notes').text("Llista ordenada per ID");
				posOrdenar= 1;
			})
			
			$('#llistaPerNom').on('click',function(e){				
				ordenarListadoEmpleado('NOM');
				$('#notes').text("Llista ordenada per nom");
				posOrdenar= 2;
			})						
			
			$('#llistaPerFeina').on('click',function(e){				
				ordenarListadoEmpleado('FEINA');
				$('#notes').text("Llista ordenada per feina");
				posOrdenar= 3;
			})				

			$('#llistaPerSalari').on('click',function(e){			
				ordenarListadoEmpleado('SALARI');
				$('#notes').text("Llista ordenada per salari");
				posOrdenar= 4;
			})				
			
	

			$('#infoSQL').on('click',function(){
				$('#divSql').slideToggle(1000);
				if ($('#infoSQL').text() == 'Info SQL'){
					$('#infoSQL').text('Amagar SQL');
					$('#notes').text("SQL Script visible");
				}else{
					$('#infoSQL').text('Info SQL');
					$('#notes').text("SQL Script amagat");
					clickPorSeleccion();
				}
			});					
		});	
			