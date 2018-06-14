$(document).ready(function(){
	//Ajax Get prob by etab
	if($("input#mode").val() == "edit") $("#profs").show();
	
	$("#etab").change(function(){
		if(this.value == ""){
			$("#professeurs").find('option.opts-pp').remove().end();
			$("#profs").hide();
		}
		else {
			$.ajax({
			    type: 'GET',
			    url: 'http://localhost:8080/GestionScolaireSpringWeb/classe/getEtabProf',
			    data:{idetab:this.value}
			}).done(function(data){
				$("#professeurs").find('option.opts-pp').remove().end();
				$("#professeurs").append(data);
			});
			
			$("#profs").show();
		}
	});
		
	$('#calendar').fullCalendar({
		header: {
			left: 'prev,next today',
			center: 'title',
			right: 'month,agendaWeek,agendaDay'
		},
		locale:'fr',
		defaultDate: today(),
		navLinks: true, // can click day/week names to navigate views
		selectable: true,
		selectHelper: true,
		select: function(start, end) {
			$('#modal').modal('show');
			var eventData;
			$("#button-form-event").bind('click',function(){
				start = moment(start).format('YYYY-MM-DD HH:mm');
				end = moment(end).format('YYYY-MM-DD HH:mm');
				
				var classeId = $("input[name='classe_id']").val(),
					etabId = $("input[name='etab_id']").val(),
					profId = $("select[name='personne_id'] option:selected").val(),
					matId = $("select[name='matiere_id'] option:selected").val(),
					salleId = $("select[name='salle_id'] option:selected").val();
			
				
				$.ajax({
					url:'http://localhost:8080/GestionScolaireSpringWeb/evenement/add',
					method:'POST',
					data:{
						personne_id:profId,
						salle_id:salleId,
						matiere_id:matId,
						classe_id:classeId, 
						etab_id:etabId,
						dateD: start,
						dateF: end
					},
					success:function(datas){
						
						$("#calendar").fullCalendar('refetchEvents');	
					}
				});
				$("#button-form-event").off("click");
				
			});
					
			$('#calendar').fullCalendar('unselect');
		},
		editable: true,
		eventLimit: true, // allow "more" link when too many events
		events: {
			url:'http://localhost:8080/GestionScolaireSpringWeb/evenement/getEvents',
			type:"POST",
			dataType:"json",
			data:{classe_id:$("input[name='classe_id']").val()},
	        error: function() {
	            alert('Erreur pendant le chargement des evenements');
	        },
	    },
	    eventDrop: function(event, delta) {
	    	start = moment(event.start).format('YYYY-MM-DD HH:mm');
			end = moment(event.end).format('YYYY-MM-DD HH:mm');
			id = event.evenementid.toString();

	    	$.ajax({
				url:'http://localhost:8080/GestionScolaireSpringWeb/evenement/edit',
				method:'POST',
				data:{	
					eventid:id,
					dateD: start,
					dateF: end
				},
				success:function(datas){
					$("#calendar").fullCalendar('refetchEvents');	
				}
			});
	    },
	    eventResize: function(event) {
	    	start = moment(event.start).format('YYYY-MM-DD HH:mm');
			end = moment(event.end).format('YYYY-MM-DD HH:mm');
			id = event.evenementid.toString();
			
	    	$.ajax({
				url:'http://localhost:8080/GestionScolaireSpringWeb/evenement/edit',
				method:'POST',
				data:{	
					eventid:id,
					dateD: start,
					dateF: end
				},
				success:function(datas){
					
					$("#calendar").fullCalendar('refetchEvents');	
				}
			});
	    },
		eventRender: function(event, element) {
	        element.append("<div data-id='"+event.evenementid+"'></div>");
	        element.append("<p class='title-event'><b>Professeur : </b></b></p><p class='descr-event'>"+event.prof+"</p>");
	        element.append("<p class='title-event'><b>Matière : </b></p><p class='descr-event'>"+event.matiere+"</p>"); 
	        element.append("<p class='title-event'> <b>Salle: </b></p><p class='descr-event'>"+event.salle+"</p>"); 
	        element.append('<a href="#" class="close-event" data-toggle="modal" data-target="#confirm-delete">&times;</a>')
	       	element.find(".close-event").click(function() {
	        	id = event.evenementid.toString();
	        	
				console.log(event);
				$(".btn-del").bind('click',function(){
		    	$.ajax({
					url:'http://localhost:8080/GestionScolaireSpringWeb/evenement/delete',
					method:'POST',
					data:{	
						eventid:id,
					},
					success:function(datas){
			           $('#calendar').fullCalendar('removeEvents',event._id);	
					}
				});
		    	$(".btn-del").off("click");
				
				});
            })
		},
		eventClick: function(event) {
			start = moment(event.start).format('DD-MM-YYYY HH:mm');
			end = moment(event.end).format('HH:mm');
			$("#viewevent .modal-header .modal-title").remove();
			$("#viewevent .modal-body .infevent").remove();
			$("#viewevent .modal-header").append("<div class='modal-title'> Evenement du :"+start+" à "+end+"</div>");
			
			$("#viewevent .modal-body").append("<div class='infevent'><p>Professeur : "+event.prof+"</p><p>Matiere : "+event.matiere+"</p><p>Salle : "+event.salle+"</p></div>");
			$("#viewevent").modal('show');
	        
	    }

	});
	$('input#couleurMatiere').simpleColorPicker();
});

function today(){
	var today = new Date();
	var dd = today.getDate();
	var mm = today.getMonth()+1; //January is 0!
	var yyyy = today.getFullYear();

	if(dd<10) {
	    dd = '0'+dd
	} 

	if(mm<10) {
	    mm = '0'+mm
	} 

	today = yyyy + '-' + mm + '-' + dd;
	return today;
}