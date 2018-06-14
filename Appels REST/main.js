var clients = [];
var immeubles = [];
var appartements = [];
var maisons = [];
var firstLoad;

window.onload = function() {
	init();
}

function init() {
	firstLoad = true;
	loadClients();	
	loadMaisons();
	loadImmeubles();
	firstLoad = false;
	loadAppartements();
}

function loadClients() {
	jQuery.getJSON("http://localhost:8081/bigbisous/api/clients", function(data) {
		console.log("success");
		let items=[];
		$.each(data, function(key, val){
			console.log("item : " + key);
			items.push(val);
		});
		clients = items;
		if (!firstLoad) {
			drawClients();			
		}
	});
}

function loadAppartements() {
	jQuery.getJSON("http://localhost:8081/bigbisous/api/appartements", function(data) {
		console.log("success");
		let items=[];
		$.each(data, function(key, val){
			console.log("item : " + key);
			items.push(val);
		});
		appartements = items;
		if (!firstLoad) {
			drawAppartements();			
		}
	});
}

function loadMaisons() {
	jQuery.getJSON("http://localhost:8081/bigbisous/api/maisons", function(data) {
		console.log("success");
		let items=[];
		$.each(data, function(key, val){
			console.log("item : " + key);
			items.push(val);
		});
		maisons = items;
		if (!firstLoad) {
			drawMaisons();			
		}
	});
}

function loadImmeubles() {
	jQuery.getJSON("http://localhost:8081/bigbisous/api/immeubles", function(data) {
		console.log("success");
		let items=[];
		$.each(data, function(key, val){
			console.log("item : " + key);
			items.push(val);
		});
		immeubles = items;
		if (!firstLoad) {
			drawImmeubles();			
		}
	});
}

function drawClients() {
	
	console.log("drawClients");
	
	let tabBody = $("tbody", $("#valueTable"));

	let rows = $("tr", tabBody);
	
	for (let i = rows.length - 1; i >= 0; i--) {
		let row = rows.get(i);
		console.log(i + ":" + row);
		tabBody.get(0).removeChild(row);
	}
	
	let tabHead = $("thead", $("#valueTable"));

	var cols = $("th", tabHead);
	
	for (let i = cols.length - 1; i >= 0; i--) {
		let col = cols.get(i);
		console.log(i + ":" + col);
		tabHead.get(0).removeChild(col);
	}
	
	console.log("suppr ok");
	
	let headId = $("<th/>");
	headId.text("ID");
	headId.appendTo(tabHead);
	
	let headNom = $("<th/>");
	headNom.text("Nom");
	headNom.appendTo(tabHead);
	
	let headPrenom = $("<th/>");
	headPrenom.text("Prenom");
	headPrenom.appendTo(tabHead);
	
	let headAdresse = $("<th/>");
	headAdresse.text("Adresse");
	headAdresse.appendTo(tabHead);
	
	let headLog = $("<th/>");
	headLog.text("Logements");
	headLog.appendTo(tabHead);
	
	let headActions = $("<th/>");
	headActions.text("Actions");
	headActions.appendTo(tabHead);
	
	for (let i = 0; i < clients.length; i++) {
		
		console.log("client " + i);
		let client = clients[i];
		let row = $("<tr/>");
		let idCell = $("<td/>");
		idCell.text(client.id);
		idCell.appendTo(row);

		let nomCell = $("<td/>");
		nomCell.text(client.nom);
		nomCell.appendTo(row);

		let prenomCell = $("<td/>");
		prenomCell.text(client.prenom);
		prenomCell.appendTo(row);

		let adresseCell = $("<td/>");
		adresseCell.text(client.adresse);
		adresseCell.appendTo(row);
		
		let logementsCell = $("<td/>");
		let list = ""
		for (let logement of client.logements) {
			list += logement.id + "<br/>";
		}
		
		logementsCell.html(list);
		logementsCell.appendTo(row);

		let buttonsCell = $("<td/>");
		buttonsCell.html("<div class='btn-group'><button type='button' data-toggle='modal' data-target='#formModal'  class='btn btn-info btn-sm' onclick='editClient("+client.id+")'><span class='fas fa-edit'></span></button><button type='button' class='btn btn-danger btn-sm' onclick='delClient("
				+ client.id
				+ ")'><span class='fas fa-trash'></span></button></div>");
		buttonsCell.appendTo(row);

		row.appendTo(tabBody);
	}
	
}

function drawAppartements() {
	
	console.log("drawAppartements");
	
	console.log("drawClients");
	
	let tabBody = $("tbody", $("#valueTable"));

	let rows = $("tr", tabBody);
	
	for (let i = rows.length - 1; i >= 0; i--) {
		let row = rows.get(i);
		console.log(i + ":" + row);
		tabBody.get(0).removeChild(row);
	}
	
	let tabHead = $("thead", $("#valueTable"));

	let cols = $("th", tabHead);
	
	for (let i = cols.length - 1; i >= 0; i--) {
		let col = cols.get(i);
		console.log(i + ":" + col);
		tabHead.get(0).removeChild(col);
	}
	
	console.log("suppr ok");
	
	let headId = $("<th/>");
	headId.text("ID");
	headId.appendTo(tabHead);
	
	let headNb = $("<th/>");
	headNb.text("Nombre de pièces");
	headNb.appendTo(tabHead);
	
	let headSup = $("<th/>");
	headSup.text("Superficie");
	headSup.appendTo(tabHead);
	
	let headClient = $("<th/>");
	headClient.text("Client");
	headClient.appendTo(tabHead);
	
	let headNumappt = $("<th/>");
	headNumappt.text("Numero d'appt");
	headNumappt.appendTo(tabHead);
	
	let headEtage = $("<th/>");
	headEtage.text("Etage");
	headEtage.appendTo(tabHead);
	
	let headAImmeuble = $("<th/>");
	headAImmeuble.text("Immeuble");
	headAImmeuble.appendTo(tabHead);
	
	let headActions = $("<th/>");
	headActions.text("Actions");
	headActions.appendTo(tabHead);
	
	for (let i = 0; i < appartements.length; i++) {
		
		console.log("appartement " + i);
		
		let appt = appartements[i];
		let row = $("<tr/>");
		
		let idCell = $("<td/>");
		idCell.text(appt.id);
		idCell.appendTo(row);

		let nbCell = $("<td/>");
		nbCell.text(appt.nombrePiece);
		nbCell.appendTo(row);

		let prenomCell = $("<td/>");
		prenomCell.text(appt.superficie);
		prenomCell.appendTo(row);

		let adresseCell = $("<td/>");
		adresseCell.text(appt.client.id);
		adresseCell.appendTo(row);
		
		let numeroCell = $("<td/>");
		numeroCell.text(appt.numero);
		numeroCell.appendTo(row);
		
		let etageCell = $("<td/>");
		etageCell.text(appt.etage);
		etageCell.appendTo(row);
		
		let immeubleCell = $("<td/>");
		immeubleCell.text(appt.immeuble.id);
		immeubleCell.appendTo(row);

		let buttonsCell = $("<td/>");
		buttonsCell.html("<div class='btn-group'><button type='button' data-toggle='modal' data-target='#formModal'  class='btn btn-info btn-sm' onclick='editAppartement("+appt.id+")'><span class='fas fa-edit'></span></button><button type='button' class='btn btn-danger btn-sm' onclick='deleteAppartement("
				+ appt.id
				+ ")'><span class='fas fa-trash'></span></button></div>");
		buttonsCell.appendTo(row);

		row.appendTo(tabBody);
	}
	
}

function drawMaisons() {
	
	console.log("drawMaisons");

	let tabBody = $("tbody", $("#valueTable"));

	let rows = $("tr", tabBody);
	
	for (let i = rows.length - 1; i >= 0; i--) {
		let row = rows.get(i);
		console.log(i + ":" + row);
		tabBody.get(0).removeChild(row);
	}
	
	let tabHead = $("thead", $("#valueTable"));

	let cols = $("th", tabHead);
	
	for (let i = cols.length - 1; i >= 0; i--) {
		let col = cols.get(i);
		console.log(i + ":" + col);
		tabHead.get(0).removeChild(col);
	}
	
	console.log("suppr ok");
	
	let headId = $("<th/>");
	headId.text("ID");
	headId.appendTo(tabHead);
	
	let headNb = $("<th/>");
	headNb.text("Nombre de pièces");
	headNb.appendTo(tabHead);
	
	let headSup = $("<th/>");
	headSup.text("Superficie");
	headSup.appendTo(tabHead);
	
	let headClient = $("<th/>");
	headClient.text("Client");
	headClient.appendTo(tabHead);
	
	let headAdresse = $("<th/>");
	headAdresse.text("Adresse");
	headAdresse.appendTo(tabHead);
	
	let headCode = $("<th/>");
	headCode.text("Code postal");
	headCode.appendTo(tabHead);
	
	let headActions = $("<th/>");
	headActions.text("Actions");
	headActions.appendTo(tabHead);
	
	for (let i = 0; i < maisons.length; i++) {
		
		console.log("maison " + i);
		
		let maison = maisons[i];
		let row = $("<tr/>");
		
		let idCell = $("<td/>");
		idCell.text(maison.id);
		idCell.appendTo(row);

		let nbCell = $("<td/>");
		nbCell.text(maison.nombrePiece);
		nbCell.appendTo(row);

		let prenomCell = $("<td/>");
		prenomCell.text(maison.superficie);
		prenomCell.appendTo(row);

		let clientCell = $("<td/>");
		clientCell.text(maison.client.id);
		clientCell.appendTo(row);
		
		let adresseCell = $("<td/>");
		adresseCell.text(maison.adresse);
		adresseCell.appendTo(row);
		
		let codeCell = $("<td/>");
		codeCell.text(maison.codePostal);
		codeCell.appendTo(row);		

		let buttonsCell = $("<td/>");
		buttonsCell.html("<div class='btn-group'><button type='button' data-toggle='modal' data-target='#formModal'  class='btn btn-info btn-sm' onclick='editMaison("+maison.id+")'><span class='fas fa-edit'></span></button><button type='button' class='btn btn-danger btn-sm' onclick='deleteMaison("
				+ maison.id
				+ ")'><span class='fas fa-trash'></span></button></div>");
		buttonsCell.appendTo(row);

		row.appendTo(tabBody);
	}
	
}

function drawImmeubles() {
	
	console.log("drawImmeubles");
	
	let tabBody = $("tbody", $("#valueTable"));

	let rows = $("tr", tabBody);
	
	for (let i = rows.length - 1; i >= 0; i--) {
		let row = rows.get(i);
		console.log(i + ":" + row);
		tabBody.get(0).removeChild(row);
	}
	
	let tabHead = $("thead", $("#valueTable"));

	let cols = $("th", tabHead);
	
	for (let i = cols.length - 1; i >= 0; i--) {
		let col = cols.get(i);
		console.log(i + ":" + col);
		tabHead.get(0).removeChild(col);
	}
	
	console.log("suppr ok");
	
	let headId = $("<th/>");
	headId.text("ID");
	headId.appendTo(tabHead);
	
	let headAdresse = $("<th/>");
	headAdresse.text("Adresse");
	headAdresse.appendTo(tabHead);
	
	let headCode = $("<th/>");
	headCode.text("Code postal");
	headCode.appendTo(tabHead);
	
	let headAppartements = $("<th/>");
	headAppartements.text("Appartements");
	headAppartements.appendTo(tabHead);
	
	let headActions = $("<th/>");
	headActions.text("Actions");
	headActions.appendTo(tabHead);
	
	for (let i = 0; i < immeubles.length; i++) {
		
		console.log("immeuble " + i);
		
		let immeuble = immeubles[i];
		let row = $("<tr/>");
		
		let idCell = $("<td/>");
		idCell.text(immeuble.id);
		idCell.appendTo(row);

		
		let adresseCell = $("<td/>");
		adresseCell.text(immeuble.adresse);
		adresseCell.appendTo(row);
		
		let codeCell = $("<td/>");
		codeCell.text(immeuble.codePostal);
		codeCell.appendTo(row);	

		let apptsCell = $("<td/>");
		
		let list = "<div class='row'><div class='col-sm-6'>"
		for (let appt of immeuble.appartements) {
			list += appt.id + "<br/>";
		}
		list+="</div><div class='col-sm-6' style='text-align:center'>"
		list += "<button type='button' class='btn btn-info btn-sm' onclick='addAppartementToImmeuble("+ immeuble.id +")'><span class='fas fa-plus-square'></span></button></div></div>"
		
		apptsCell.html(list);
		apptsCell.appendTo(row);	

		let buttonsCell = $("<td/>");
		buttonsCell.html("<div class='btn-group'><button type='button' data-toggle='modal' data-target='#formModal'  class='btn btn-info btn-sm' onclick='editImmeuble("+immeuble.id+")'><span class='fas fa-edit'></span></button><button type='button' class='btn btn-danger btn-sm' onclick='delImmeuble("
				+ immeuble.id
				+ ")'><span class='fas fa-trash'></span></button></div>");
		buttonsCell.appendTo(row);

		row.appendTo(tabBody);
	}
	
}

function createClient() {
	let myClient = {
		"id":"",
		"nom":$("#inputNomClient").val(),
		"prenom":$("#inputPrenomClient").val(),
		"adresse":$("#inputAdresseClient").val()
	}
	
	pushClient(myClient);
}

function pushClient(myClient) {
		
	$.ajax({
            url: "http://localhost:8081/bigbisous/api/clients", // A valid URL
            type: 'PUT', // Use POST with X-HTTP-Method-Override or a straight PUT if appropriate.
            data: JSON.stringify(myClient), // The body
            contentType : 'application/json',	// The content type we will pass in our body
            success: function(data) {
                loadClients();
            }
        });
}

function delClient(id) {
	$.ajax({
            url: "http://localhost:8081/bigbisous/api/clients/"+id, // A valid URL
            type: 'DELETE', // Use POST with X-HTTP-Method-Override or a straight PUT if appropriate
            success: function(data) {
                loadClients();
            }
        });
}

function editClient(id) {
	$("#row_id_client").attr("style", "visibility:visible");
	
	$("#inputIDClient").val(id);
	
	
	for (let client of clients) {
		if (client.id == id) {
			$("#inputNomClient").val(client.nom);
			$("#inputPrenomClient").val(client.prenom);
			$("#inputAdresseClient").val(client.adresse);
		}
	}
	
	$("#clientValidateButton").attr("onclick", "updateClient()");$("#clientValidateButton").text("Mettre à jour");
	$("#modalClientsLabel").text("Modification de client");
	
	$("#createClientModalButton").click();

	
}

function clientReset() {
	$("#clientValidateButton").attr("onclick", "createClient()");	$("#clientValidateButton").text("Créer");
	$("#row_id_client").attr("style", "visibility:hidden");
	$("#modalClientsLabel").text("Création de client");
}

function updateClient() {
	let myClient = {
		"id":$("#inputIDClient").val(),
		"nom":$("#inputNomClient").val(),
		"prenom":$("#inputPrenomClient").val(),
		"adresse":$("#inputAdresseClient").val()
	}
	
	pushClient(myClient);
	
	clientReset();
}

function createImmeuble() {
	let myImmeuble = {
		"id":"",
		"adresse":$("#inputAdresseImmeuble").val(),
		"codePostal":$("#inputCodeImmeuble").val()
	}
	
	pushImmeuble(myImmeuble);
}

function pushImmeuble(myImmeuble) {
		
	$.ajax({
            url: "http://localhost:8081/bigbisous/api/immeubles", // A valid URL
            type: 'PUT', // Use POST with X-HTTP-Method-Override or a straight PUT if appropriate.
            data: JSON.stringify(myImmeuble), // The body
            contentType : 'application/json',	// The content type we will pass in our body
            success: function(data) {
                loadImmeubles();
            }
        });
}

function delImmeuble(id) {
	$.ajax({
            url: "http://localhost:8081/bigbisous/api/immeubles/"+id, // A valid URL
            type: 'DELETE', // Use POST with X-HTTP-Method-Override or a straight PUT if appropriate
            success: function(data) {
                loadImmeubles();
            }
        });
}

function editImmeuble(id) {
	$("#row_id_immeuble").attr("style", "visibility:visible");
	
	$("#inputIDImmeuble").val(id);
	
	
	for (let immeuble of immeubles) {
		if (immeuble.id == id) {
			$("#inputAdresseImmeuble").val(immeuble.adresse);
			$("#inputCodeImmeuble").val(immeuble.codePostal);
		}
	}
	
	$("#immeubleValidateButton").attr("onclick", "updateImmeuble()");
	$("#immeubleValidateButton").text("Mettre à jour");
	$("#modalImmeublesLabel").text("Modification d'immeuble");
	
	$("#createImmeubleModalButton").click();
	
}

function immeubleReset() {
	$("#immeubleValidateButton").attr("onclick", "createImmeuble()");	$("#immeubleValidateButton").text("Créer");
	$("#row_id_immeuble").attr("style", "visibility:hidden");
	$("#modalImmeublesLabel").text("Création d'immeuble");
}

function updateImmeuble() {
	let myImmeuble = {
		"id":$("#inputIDImmeuble").val(),
		"adresse":$("#inputAdresseImmeuble").val(),
		"codePostal":$("#inputCodeImmeuble").val()
	}
	
	pushImmeuble(myImmeuble);
	
	clientReset();
}

function addAppartementToImmeuble(id) {
	console.log("caca");
	let select = $("#appartementAjoutSelect");
	for (let appt of appartements) {
		let opt = $("<option/>");
		opt.val(appt.id);
		opt.text("Appartement " + appt.id);
		opt.appendTo(select);
	}
	
	$("#row_id_immeuble_2").val(id);
	
	$("#modalAjoutAppartementsImmeuble").modal('show');
}

function pushAppartementImmeuble() {
	
	let idImmeuble = $("#row_id_immeuble_2").val();
	let idAppartement = $("#appartementAjoutSelect").val();
	
	let myurl = "http://localhost:8081/bigbisous/api/immeubles/" + idImmeuble + "/logement/" + idAppartement;
	
	$.ajax({
            url: myurl, // A valid URL
            type: 'PUT', // Use POST with X-HTTP-Method-Override or a straight PUT if appropriate
            success: function(data) {
                loadImmeubles();
            }
        });
}









