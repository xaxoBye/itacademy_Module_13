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




function borrar(idParam){
	var id = idParam.target.id;
	var separados ="";
	if(id.includes("txt")){
		separados = id.split("-");	
		id = separados[0];		
	}
	alert("localhost:8080/delete/" + id);
//	window.location.href = "\'delete/" + id +"\'";
}