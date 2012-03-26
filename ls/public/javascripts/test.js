/**
 * 
 */
	function showDialog(opc) {
		console.debug(opc);
		var formDlg = (opc=='edit') ? dijit.byId('formDialogoEditar') : dijit.byId('formDialogo');
		console.log('opcion usr:'+ opc);
		global_opc = opc;
		if (opc == 'add') {
			try{
				document.getElementById('edit0__add1').value = '';
			}catch(e){console.log(e)}
			try{
				document.getElementById('edit0__add2').value = '';
			}catch(e){console.log(e)}
			try{
				dijit.byId('edit0__add3').set('value',0);
			}catch(e){console.log(e)}
			try{
				dijit.byId('edit0__add4').set('value',0);
			}catch(e){console.log(e)}
			try{
				dijit.byId('edit0__add5').set('value',0);
			}catch(e){console.log(e)}
			formDlg.set('title','Agregar M&oacute;dulos');
		} else if(opc == 'edit') {
			var items = main_grid.selection.getSelected();
			if (items[0]==undefined) {
				alert('Debes seleccionar una fila');
				return;
			}
			console.debug(items[0]);
			try{
				document.getElementById('edit0_1').value = items[0].title;
			}catch(e){console.log(e)}
			try{
				document.getElementById('edit0_2').value = items[0].module;
			}catch(e){console.log(e)}
			try{
				dijit.byId('edit0_3').set('value',items[0].parentId);
			}catch(e){console.log(e)}
			try{
				document.getElementById('edit0_4').value = items[0].tree;
			}catch(e){console.log(e)}
			try{
				document.getElementById('edit0_5').value = items[0].linkable;
			}catch(e){console.log(e)}
			try{formDlg.set('title','Editar M&oacute;dulos');}catch(e){console.debug(e);}
		}
		formDlg.show();
	}

	function modify(model, items) {
		var resp = '';
		if(global_opc == 'add') {
			resp = insertar(model,items);
		} else if(global_opc == 'edit') {
			var items = main_grid.selection.getSelected();
			var id = items[0].id;
			resp = actualizar(model, items, id);
		}
		if(resp.message != '' && resp.message != null){
			alert(resp.message);
		}else if(resp.state == 'UPDATE_OK'){
			alert('Datos Actualizados');
		}else if(resp.state == 'ADD_OK'){
			alert('Datos Ingresados');
		}else if(resp.state == 'UPDATE_FAIL'){
			alert('Ha ocurrido un error, o no ha modificado datos');
		}else if(resp.state == 'ADD_FAIL'){
			alert('Ha ocurrido un error, verifique datos o intente más tarde');
		}
		cargarDatos(model);
	}

	function insertar(model, items) {
		var res = '';
		dojo.xhrPost( {
		url: base_url+'objects',
		content: {
			'data[title]' : document.getElementById('edit0__add1').value, 
			'data[module]' : document.getElementById('edit0__add2').value, 
			'data[parentId]' : dijit.byId('edit0__add3').get('value'), 
			'data[tree]' : document.getElementById('edit0__add4').value, 
			'data[linkable]' : document.getElementById('edit0__add5').value, 
			'action'      :'add',
			'model'     : model,
			'format'    : 'json'
			},
			handleAs: 'json',
			sync: true,
			preventCache: true,
			timeout: 5000,
			load: function(respuesta){
			console.debug(dojo.toJson(respuesta));
							 dijit.byId('edit0__add3').set('value', '');
				 dijit.byId('edit0__add4').set('value', '');
				 dijit.byId('edit0__add5').set('value', '');

			res = respuesta;
			return respuesta;
			},
			error:function(err){
			alert('Ha ocurrido un error, por favor reinicie sesión');
			window.location.href = base_url + 'index/login';
			return err;
			}
		});
		return res;
	}

	function actualizar(model, items, id) {
		var res = '';
		dojo.xhrPost( {
		url: base_url+'objects',
		content: {
			'data[title]' : document.getElementById('edit0_1').value, 
			'data[module]' : document.getElementById('edit0_2').value, 
			'data[parent_id]' : dijit.byId('edit0_3').get('value'), 
			'data[tree]' : dijit.byId('edit0_4').get('value'), 
			'data[linkable]' : dijit.byId('edit0_5').get('value'), 
			'id'      :id,
			'action'  :'edit',
			'model'   : model,
			'format'  : 'json'
		},
		handleAs: 'json',
			sync: true,
			preventCache: true,
			timeout: 5000,
			load: function(respuesta){
				console.debug(dojo.toJson(respuesta));
						 dijit.byId('edit0__add3').set('value', '');
				 dijit.byId('edit0__add4').set('value', '');
				 dijit.byId('edit0__add5').set('value', '');

			res = respuesta;
			return respuesta;
			},
			error:function(err){
			//alert('Error en comunicacion de datos. error: '+err); alert('Ha ocurrido un error, por favor reinicie sesión');
			window.location.href = base_url + 'index/login';
			return err;
			}
		});
		return res;
	}

