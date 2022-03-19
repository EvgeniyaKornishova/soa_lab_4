(window.webpackJsonp=window.webpackJsonp||[]).push([[6],{434:function(e,t){},435:function(e,t){},551:function(e,t,n){"use strict";n.r(t),n.d(t,"URL_BASE",(function(){return v}));var r=n(24),o=(n(88),n(34),n(57),n(25),n(10),n(75),n(55),n(424)),l=o.parseString,c=new(0,o.Builder),d=[{text:"ID",value:"id"},{text:"Name",value:"name"},{text:"Coordinates",value:"coordinates"},{text:"Creation date",value:"creationDate"},{text:"Height",value:"height"},{text:"Eye color",value:"eyeColor"},{text:"Hair color",value:"hairColor"},{text:"Nationality",value:"nationality"},{text:"Location",value:"location"},{text:"",value:"actions"}],m=[{text:"X",value:"x"},{text:"Y",value:"y"},{text:"Z",value:"z"}],v="https://localhost:5052/api",f={data:function(){return{error:null,headers:d,locationHeaders:m,persons:[],uniqueLocations:[],dialog:!1,dialogDelete:!1,editedIndex:-1,idForGettingPerson:null,heightSum:null,substring:null,page:1,pageCount:1,itemsPerPage:10,vItemsPerPage:10,currentSort:"id",itemsCount:0,getPersonAlertOn:!1,tableAlertOn:!1,availableColors:["Green","White","Black","Yellow","Orange"],availableCountries:["India","Vatican","North Korea","Japan"],sortItems:["Id","Name","Height","Eye color","Hair color","Nationality","Creation date","Coordinates X","Coordinates Y","Location X","Location Y","Location Z"],filter:{name:"",coordinates:{x:"",y:""},creationDate:"",height:"",eyeColor:"",hairColor:"",nationality:"",location:{x:"",y:"",z:""}},editedItem:{id:"",name:"",coordinates:{x:"",y:""},creationDate:"",height:"",eyeColor:"",hairColor:"",nationality:"",location:{x:"",y:"",z:""}},defaultItem:{id:"",name:"",coordinates:{x:"",y:""},creationDate:"",height:"",eyeColor:"",hairColor:"",nationality:"",location:{x:"",y:"",z:""}},itemByID:{id:null,name:null,coordinates:{x:null,y:null},creationDate:null,height:null,eyeColor:null,hairColor:null,nationality:null,location:{x:null,y:null,z:null}}}},computed:{formTitle:function(){return-1===this.editedIndex?"New Item":"Edit Item"}},watch:{dialog:function(e){e||this.close()},dialogDelete:function(e){e||this.closeDelete()}},methods:{initialize:function(){var e=this;return Object(r.a)(regeneratorRuntime.mark((function t(){var n,r,o,c;return regeneratorRuntime.wrap((function(t){for(;;)switch(t.prev=t.next){case 0:return e.recountPages(),n=[{name:"page_id",value:e.page},{name:"page_size",value:e.itemsPerPage},{name:"sort",value:e.currentSort.toLowerCase().replace(" ","_")},{name:"name",value:e.filter.name},{name:"eye_color",value:e.filter.eyeColor.toUpperCase()},{name:"hair_color",value:e.filter.hairColor.toUpperCase()},{name:"nationality",value:e.filter.nationality.toUpperCase().replace(" ","_")},{name:"creation_date",value:e.filter.creationDate},{name:"height",value:e.filter.height},{name:"location_x",value:e.filter.location.x},{name:"location_y",value:e.filter.location.y},{name:"location_z",value:e.filter.location.z},{name:"coordinates_x",value:e.filter.coordinates.x},{name:"coordinates_y",value:e.filter.coordinates.y}],t.next=4,e.$axios.$get("".concat(v,"/persons?")+n.filter((function(param){return""!=param.value})).map((function(param){return param.name+"="+param.value})).join("&"));case 4:r=t.sent,o=[],c=0,l(r,{explicitArray:!1},(function(e,t){c=t.persons.$.count,void 0!==t.persons.person&&(t.persons.person.constructor===Array?o=t.persons.person:o.push(t.persons.person))})),e.itemsCount=c,e.persons=o.map((function(e){return e.eyeColor=e.eyeColor.toLowerCase().replace(/^\w/,(function(e){return e.toUpperCase()})),e.hairColor=e.hairColor.toLowerCase().replace(/^\w/,(function(e){return e.toUpperCase()})),e.nationality=e.nationality.toLowerCase().replace(/^\w/,(function(e){return e.toUpperCase()})).replace(/_\w/,(function(e){return e.toUpperCase()})).replace("_"," "),e})),e.recountPages();case 11:case"end":return t.stop()}}),t)})))()},getHeightSum:function(){var e=this;return Object(r.a)(regeneratorRuntime.mark((function t(){var n,r;return regeneratorRuntime.wrap((function(t){for(;;)switch(t.prev=t.next){case 0:return t.next=2,e.$axios.$get("".concat(v,"/persons/heights/sum"));case 2:n=t.sent,l(n,{explicitArray:!1},(function(e,t){r=t.server_response.body._})),e.heightSum=r;case 5:case"end":return t.stop()}}),t)})))()},getPersonById:function(){var e=this;return Object(r.a)(regeneratorRuntime.mark((function t(){var n,r;return regeneratorRuntime.wrap((function(t){for(;;)switch(t.prev=t.next){case 0:return e.getPersonAlertOn=!1,isNaN(e.idForGettingPerson)&&(e.getPersonAlertOn=!0,e.error="Person id must be a number"),t.prev=2,t.next=5,e.$axios.$get("".concat(v,"/persons/")+e.idForGettingPerson);case 5:n=t.sent,l(n,{explicitArray:!1},(function(e,t){r=t.person})),e.itemByID=r,t.next=14;break;case 10:t.prev=10,t.t0=t.catch(2),e.error=t.t0.response.data,e.getPersonAlertOn=!0;case 14:case"end":return t.stop()}}),t,null,[[2,10]])})))()},getPersonsBySubstring:function(){var e=this;return Object(r.a)(regeneratorRuntime.mark((function t(){var n,r;return regeneratorRuntime.wrap((function(t){for(;;)switch(t.prev=t.next){case 0:return t.next=2,e.$axios.$get("".concat(v,"/persons/names/search?name=")+e.substring);case 2:n=t.sent,r=[],l(n,{explicitArray:!1},(function(e,t){""!=t.persons&&(t.persons.item.constructor===Array?r=t.persons.item:r.push(t.persons.item))})),e.persons=r;case 6:case"end":return t.stop()}}),t)})))()},getUniqueLocations:function(){var e=this;return Object(r.a)(regeneratorRuntime.mark((function t(){var n,r;return regeneratorRuntime.wrap((function(t){for(;;)switch(t.prev=t.next){case 0:return t.next=2,e.$axios.$get("".concat(v,"/persons/locations/uniq"));case 2:n=t.sent,r=[],l(n,{explicitArray:!1},(function(e,t){""!=t.locations&&(t.locations.item.constructor===Array?r=t.locations.item:r.push(t.locations.item))})),e.uniqueLocations=r;case 6:case"end":return t.stop()}}),t)})))()},paginationUpdate:function(e){var t=this;return Object(r.a)(regeneratorRuntime.mark((function n(){var r;return regeneratorRuntime.wrap((function(n){for(;;)switch(n.prev=n.next){case 0:return r=parseInt(e,10),isNaN(r)||r<1?t.itemsPerPage=1:t.itemsPerPage=r,t.vItemsPerPage=t.itemsPerPage,n.next=5,t.initialize();case 5:case"end":return n.stop()}}),n)})))()},editItem:function(e){this.editedIndex=this.persons.indexOf(e),this.editedItem=Object.assign({},e),this.dialog=!0},deleteItem:function(e){this.editedIndex=this.persons.indexOf(e),this.dialogDelete=!0},deleteItemConfirm:function(){var e=this;return Object(r.a)(regeneratorRuntime.mark((function t(){return regeneratorRuntime.wrap((function(t){for(;;)switch(t.prev=t.next){case 0:return t.next=2,e.deletePerson();case 2:return e.itemsCount--,t.next=5,e.initialize();case 5:e.closeDelete();case 6:case"end":return t.stop()}}),t)})))()},close:function(){var e=this;this.dialog=!1,this.$nextTick((function(){e.editedItem=Object.assign({},e.defaultItem),e.editedIndex=-1}))},closeDelete:function(){var e=this;this.dialogDelete=!1,this.$nextTick((function(){e.editedIndex=-1}))},createPerson:function(){var e=this;return Object(r.a)(regeneratorRuntime.mark((function t(){return regeneratorRuntime.wrap((function(t){for(;;)switch(t.prev=t.next){case 0:return t.prev=0,t.next=3,e.$axios.$post("".concat(v,"/persons"),c.buildObject({person:{name:e.editedItem.name,height:e.editedItem.height,eyeColor:e.editedItem.eyeColor.toUpperCase(),hairColor:e.editedItem.hairColor.toUpperCase(),nationality:e.editedItem.nationality.toUpperCase().replace(" ","_"),coordinates:e.editedItem.coordinates,location:e.editedItem.location}}));case 3:t.next=9;break;case 5:t.prev=5,t.t0=t.catch(0),e.error=t.t0.response.data,e.tableAlertOn=!0;case 9:case"end":return t.stop()}}),t,null,[[0,5]])})))()},updatePerson:function(){var e=this;return Object(r.a)(regeneratorRuntime.mark((function t(){return regeneratorRuntime.wrap((function(t){for(;;)switch(t.prev=t.next){case 0:return t.prev=0,t.next=3,e.$axios.$put("".concat(v,"/persons/")+e.persons[e.editedIndex].id,c.buildObject({person:{name:e.editedItem.name,height:e.editedItem.height,eyeColor:e.editedItem.eyeColor.toUpperCase(),hairColor:e.editedItem.hairColor.toUpperCase(),nationality:e.editedItem.nationality.toUpperCase().replace(" ","_"),coordinates:e.editedItem.coordinates,location:e.editedItem.location}}));case 3:t.next=9;break;case 5:t.prev=5,t.t0=t.catch(0),e.error=t.t0.response.data,e.tableAlertOn=!0;case 9:case"end":return t.stop()}}),t,null,[[0,5]])})))()},deletePerson:function(){var e=this;return Object(r.a)(regeneratorRuntime.mark((function t(){return regeneratorRuntime.wrap((function(t){for(;;)switch(t.prev=t.next){case 0:return t.next=2,e.$axios.$delete("".concat(v,"/persons/")+e.persons[e.editedIndex].id);case 2:case"end":return t.stop()}}),t)})))()},save:function(){var e=this;return Object(r.a)(regeneratorRuntime.mark((function t(){return regeneratorRuntime.wrap((function(t){for(;;)switch(t.prev=t.next){case 0:if(e.tableAlertOn=!1,!(e.editedIndex>-1)){t.next=8;break}return e.editedItem.id=e.persons[e.editedIndex].id,Object.assign(e.persons[e.editedIndex],e.editedItem),t.next=6,e.updatePerson();case 6:t.next=11;break;case 8:return t.next=10,e.createPerson();case 10:e.itemsCount++;case 11:return t.next=13,e.initialize();case 13:e.close();case 14:case"end":return t.stop()}}),t)})))()},reset:function(){var e=this;return Object(r.a)(regeneratorRuntime.mark((function t(){return regeneratorRuntime.wrap((function(t){for(;;)switch(t.prev=t.next){case 0:return e.page=1,e.pageCount=1,e.itemsPerPage=10,e.currentSort="id",e.filter={name:"",coordinates:{x:"",y:""},creationDate:"",height:"",eyeColor:"",hairColor:"",nationality:"",location:{x:"",y:"",z:""}},t.next=7,e.initialize();case 7:case"end":return t.stop()}}),t)})))()},recountPages:function(){this.pageCount=Math.max(Math.ceil(this.itemsCount/this.itemsPerPage),1),this.page=Math.min(this.page,this.pageCount)}},created:function(){var e=this;return Object(r.a)(regeneratorRuntime.mark((function t(){return regeneratorRuntime.wrap((function(t){for(;;)switch(t.prev=t.next){case 0:return e.$axios.setHeader("Content-Type","application/xml",["post","put"]),t.next=3,e.initialize();case 3:case"end":return t.stop()}}),t)})))()}},x=n(80),h=n(120),y=n.n(h),C=n(555),I=n(189),k=n(439),D=n(378),_=n(545),P=n(371),w=n(549),$=n(546),B=n(445),z=n(163),O=n(547),R=n(548),A=n(453),S=n(544),j=n(537),V=n(44),U=n(188),component=Object(x.a)(f,(function(){var e=this,t=e.$createElement,n=e._self._c||t;return n("div",[n("v-card",{staticClass:"mb-5"},[n("v-card-title",[e._v("The height fields sum for all objects"),n("v-spacer"),n("v-btn",{staticClass:"mb-4",attrs:{color:"primary",dark:""},on:{click:e.getHeightSum}},[e._v("Calculate")])],1),n("v-card-text",[n("v-text-field",{attrs:{label:"Result",disabled:""},model:{value:e.heightSum,callback:function(t){e.heightSum=t},expression:"heightSum"}})],1)],1),n("v-card",{staticClass:"mb-5"},[n("v-card-title",[e._v("Get person by id"),n("v-spacer"),n("v-btn",{attrs:{color:"primary"},on:{click:e.getPersonById}},[e._v("Get")])],1),n("v-card-text",[n("v-text-field",{attrs:{label:"Enter an id to get a person"},model:{value:e.idForGettingPerson,callback:function(t){e.idForGettingPerson=t},expression:"idForGettingPerson"}}),n("v-alert",{attrs:{type:"error"},model:{value:e.getPersonAlertOn,callback:function(t){e.getPersonAlertOn=t},expression:"getPersonAlertOn"}},[e._v(e._s(this.error))]),n("v-row",[n("v-col",[n("v-text-field",{attrs:{label:"Name",disabled:""},model:{value:e.itemByID.name,callback:function(t){e.$set(e.itemByID,"name",t)},expression:"itemByID.name"}}),n("v-text-field",{attrs:{label:"Creation date",disabled:""},model:{value:e.itemByID.creationDate,callback:function(t){e.$set(e.itemByID,"creationDate",t)},expression:"itemByID.creationDate"}}),n("v-text-field",{attrs:{label:"Height",disabled:""},model:{value:e.itemByID.height,callback:function(t){e.$set(e.itemByID,"height",t)},expression:"itemByID.height"}})],1),n("v-col",[n("v-text-field",{attrs:{label:"Eye color",disabled:""},model:{value:e.itemByID.eyeColor,callback:function(t){e.$set(e.itemByID,"eyeColor",t)},expression:"itemByID.eyeColor"}}),n("v-text-field",{attrs:{label:"Hair color",disabled:""},model:{value:e.itemByID.hairColor,callback:function(t){e.$set(e.itemByID,"hairColor",t)},expression:"itemByID.hairColor"}}),n("v-text-field",{attrs:{label:"Nationality",disabled:""},model:{value:e.itemByID.nationality,callback:function(t){e.$set(e.itemByID,"nationality",t)},expression:"itemByID.nationality"}})],1),n("v-col",[n("span",{staticClass:"text-h8"},[e._v("Location")]),n("v-text-field",{attrs:{label:"X",disabled:""},model:{value:e.itemByID.location.x,callback:function(t){e.$set(e.itemByID.location,"x",t)},expression:"itemByID.location.x"}}),n("v-text-field",{attrs:{label:"Y",disabled:""},model:{value:e.itemByID.location.y,callback:function(t){e.$set(e.itemByID.location,"y",t)},expression:"itemByID.location.y"}}),n("v-text-field",{attrs:{label:"Z",disabled:""},model:{value:e.itemByID.location.z,callback:function(t){e.$set(e.itemByID.location,"z",t)},expression:"itemByID.location.z"}})],1),n("v-col",[n("span",{staticClass:"text-h8"},[e._v("Coordinates")]),n("v-text-field",{attrs:{label:"X",disabled:""},model:{value:e.itemByID.coordinates.x,callback:function(t){e.$set(e.itemByID.coordinates,"x",t)},expression:"itemByID.coordinates.x"}}),n("v-text-field",{attrs:{label:"Y",disabled:""},model:{value:e.itemByID.coordinates.y,callback:function(t){e.$set(e.itemByID.coordinates,"y",t)},expression:"itemByID.coordinates.y"}})],1)],1)],1)],1),n("v-card",{staticClass:"mb-5"},[n("v-card-title",[e._v("Filter persons by name substring"),n("v-spacer"),n("v-btn",{staticClass:"mb-10",attrs:{color:"primary",dark:""},on:{click:e.getPersonsBySubstring}},[e._v("Filter")])],1),n("v-card-text",[n("v-text-field",{attrs:{label:"Enter a substring to get persons whose name contanes it"},model:{value:e.substring,callback:function(t){e.substring=t},expression:"substring"}})],1)],1),n("v-alert",{attrs:{type:"error"},model:{value:e.tableAlertOn,callback:function(t){e.tableAlertOn=t},expression:"tableAlertOn"}},[e._v(e._s(this.error))]),n("v-data-table",{staticClass:"mb-5",attrs:{headers:e.headers,items:e.persons,"items-per-page":e.itemsPerPage,"hide-default-footer":""},scopedSlots:e._u([{key:"item.coordinates",fn:function(t){var n=t.item;return[e._v(e._s("("+n.coordinates.x+", "+n.coordinates.y+")"))]}},{key:"item.location",fn:function(t){var n=t.item;return[e._v(e._s("("+n.location.x+", "+n.location.y+", "+n.location.z+")"))]}},{key:"top",fn:function(){return[n("v-toolbar",{attrs:{flat:""}},[n("v-toolbar-title",[e._v("List of persons")]),n("v-divider",{staticClass:"mx-4",attrs:{inset:"",vertical:""}}),n("v-spacer"),n("v-row",{attrs:{align:"center"}},[n("v-col",{staticClass:"d-flex",attrs:{cols:"12",sm:"5"}}),n("v-col",{staticClass:"d-flex",attrs:{cols:"12",sm:"6"}},[n("v-select",{staticClass:"mt-8",attrs:{items:e.sortItems,label:"Sort",dark:""},on:{change:e.initialize},model:{value:e.currentSort,callback:function(t){e.currentSort=t},expression:"currentSort"}})],1)],1),n("v-dialog",{attrs:{"max-width":"500px"},scopedSlots:e._u([{key:"activator",fn:function(t){var r=t.on,o=t.attrs;return[n("v-btn",e._g(e._b({staticClass:"mb-2",attrs:{color:"primary",dark:""}},"v-btn",o,!1),r),[e._v("Add person")])]}}]),model:{value:e.dialog,callback:function(t){e.dialog=t},expression:"dialog"}},[n("v-card",[n("v-card-title",[n("span",{staticClass:"text-h5"},[e._v(e._s(e.formTitle))])]),n("v-card-text",[n("v-container",[n("v-row",[n("v-col",{attrs:{cols:"12",sm:"6",md:"4"}},[n("v-text-field",{attrs:{label:"Name"},model:{value:e.editedItem.name,callback:function(t){e.$set(e.editedItem,"name",t)},expression:"editedItem.name"}}),n("v-text-field",{attrs:{label:"Height"},model:{value:e.editedItem.height,callback:function(t){e.$set(e.editedItem,"height",t)},expression:"editedItem.height"}}),n("v-select",{attrs:{items:e.availableColors,label:"Eye color"},model:{value:e.editedItem.eyeColor,callback:function(t){e.$set(e.editedItem,"eyeColor",t)},expression:"editedItem.eyeColor"}}),n("v-select",{attrs:{items:e.availableColors,label:"Hair color"},model:{value:e.editedItem.hairColor,callback:function(t){e.$set(e.editedItem,"hairColor",t)},expression:"editedItem.hairColor"}}),n("v-select",{attrs:{items:e.availableCountries,label:"Nationality"},model:{value:e.editedItem.nationality,callback:function(t){e.$set(e.editedItem,"nationality",t)},expression:"editedItem.nationality"}})],1),n("v-col",{attrs:{cols:"12",sm:"6",md:"4"}},[n("span",{staticClass:"text-h8"},[e._v("Coordinates")]),n("v-text-field",{attrs:{label:"X"},model:{value:e.editedItem.coordinates.x,callback:function(t){e.$set(e.editedItem.coordinates,"x",t)},expression:"editedItem.coordinates.x"}}),n("v-text-field",{attrs:{label:"Y"},model:{value:e.editedItem.coordinates.y,callback:function(t){e.$set(e.editedItem.coordinates,"y",t)},expression:"editedItem.coordinates.y"}})],1),n("v-col",{attrs:{cols:"12",sm:"6",md:"4"}},[n("span",{staticClass:"text-h8"},[e._v("Location")]),n("v-text-field",{attrs:{label:"X"},model:{value:e.editedItem.location.x,callback:function(t){e.$set(e.editedItem.location,"x",t)},expression:"editedItem.location.x"}}),n("v-text-field",{attrs:{label:"Y"},model:{value:e.editedItem.location.y,callback:function(t){e.$set(e.editedItem.location,"y",t)},expression:"editedItem.location.y"}}),n("v-text-field",{attrs:{label:"Z"},model:{value:e.editedItem.location.z,callback:function(t){e.$set(e.editedItem.location,"z",t)},expression:"editedItem.location.z"}})],1)],1)],1)],1),n("v-card-actions",[n("v-spacer"),n("v-btn",{attrs:{color:"blue darken-1",text:""},on:{click:e.close}},[e._v("Cancel")]),n("v-btn",{attrs:{color:"blue darken-1",text:""},on:{click:e.save}},[e._v("Save")])],1)],1)],1),n("v-dialog",{attrs:{"max-width":"500px"},model:{value:e.dialogDelete,callback:function(t){e.dialogDelete=t},expression:"dialogDelete"}},[n("v-card",[n("v-card-title",{staticClass:"text-h5"},[e._v("Are you sure you want to delete this item?")]),n("v-card-actions",[n("v-spacer"),n("v-btn",{attrs:{color:"blue darken-1",text:""},on:{click:e.closeDelete}},[e._v("Cancel")]),n("v-btn",{attrs:{color:"blue darken-1",text:""},on:{click:e.deleteItemConfirm}},[e._v("OK")]),n("v-spacer")],1)],1)],1)],1)]},proxy:!0},{key:"item.actions",fn:function(t){var r=t.item;return[n("v-icon",{staticClass:"mr-2",attrs:{small:""},on:{click:function(t){return e.editItem(r)}}},[e._v("mdi-pencil")]),n("v-icon",{attrs:{small:""},on:{click:function(t){return e.deleteItem(r)}}},[e._v("mdi-delete")])]}}])}),n("div",{staticClass:"text-center pt-2"},[n("v-pagination",{attrs:{length:e.pageCount,"total-visible":7},on:{input:e.initialize},model:{value:e.page,callback:function(t){e.page=t},expression:"page"}}),n("v-text-field",{attrs:{value:e.itemsPerPage,label:"Items per page",type:"number",min:"1",max:"15"},on:{change:function(t){return e.paginationUpdate(t)}},model:{value:e.vItemsPerPage,callback:function(t){e.vItemsPerPage=t},expression:"vItemsPerPage"}}),n("v-btn",{staticClass:"mb-5",attrs:{color:"primary"},on:{click:e.reset}},[e._v("Reset")])],1),n("v-card",{staticClass:"mb-5"},[n("v-card-title",[e._v("Advanced filter"),n("v-spacer")],1),n("v-card-text",[n("v-container",[n("v-row",[n("v-col",{attrs:{cols:"12",sm:"6",md:"4"}},[n("v-text-field",{attrs:{label:"Name"},on:{change:e.initialize},model:{value:e.filter.name,callback:function(t){e.$set(e.filter,"name",t)},expression:"filter.name"}}),n("v-text-field",{attrs:{label:"Height"},on:{change:e.initialize},model:{value:e.filter.height,callback:function(t){e.$set(e.filter,"height",t)},expression:"filter.height"}}),n("v-select",{attrs:{items:e.availableColors,label:"Eye color"},on:{change:e.initialize},model:{value:e.filter.eyeColor,callback:function(t){e.$set(e.filter,"eyeColor",t)},expression:"filter.eyeColor"}}),n("v-select",{attrs:{items:e.availableColors,label:"Hair color"},on:{change:e.initialize},model:{value:e.filter.hairColor,callback:function(t){e.$set(e.filter,"hairColor",t)},expression:"filter.hairColor"}}),n("v-select",{attrs:{items:e.availableCountries,label:"Nationality"},on:{change:e.initialize},model:{value:e.filter.nationality,callback:function(t){e.$set(e.filter,"nationality",t)},expression:"filter.nationality"}}),n("v-text-field",{attrs:{label:"Creation Date"},on:{change:e.initialize},model:{value:e.filter.creationDate,callback:function(t){e.$set(e.filter,"creationDate",t)},expression:"filter.creationDate"}})],1),n("v-col",{attrs:{cols:"12",sm:"6",md:"4"}},[n("span",{staticClass:"text-h8"},[e._v("Coordinates")]),n("v-text-field",{attrs:{label:"X"},on:{change:e.initialize},model:{value:e.filter.coordinates.x,callback:function(t){e.$set(e.filter.coordinates,"x",t)},expression:"filter.coordinates.x"}}),n("v-text-field",{attrs:{label:"Y"},on:{change:e.initialize},model:{value:e.filter.coordinates.y,callback:function(t){e.$set(e.filter.coordinates,"y",t)},expression:"filter.coordinates.y"}})],1),n("v-col",{attrs:{cols:"12",sm:"6",md:"4"}},[n("span",{staticClass:"text-h8"},[e._v("Location")]),n("v-text-field",{attrs:{label:"X"},on:{change:e.initialize},model:{value:e.filter.location.x,callback:function(t){e.$set(e.filter.location,"x",t)},expression:"filter.location.x"}}),n("v-text-field",{attrs:{label:"Y"},on:{change:e.initialize},model:{value:e.filter.location.y,callback:function(t){e.$set(e.filter.location,"y",t)},expression:"filter.location.y"}}),n("v-text-field",{attrs:{label:"Z"},on:{change:e.initialize},model:{value:e.filter.location.z,callback:function(t){e.$set(e.filter.location,"z",t)},expression:"filter.location.z"}})],1)],1)],1)],1)],1),n("v-data-table",{attrs:{headers:e.locationHeaders,items:e.uniqueLocations},scopedSlots:e._u([{key:"top",fn:function(){return[n("v-toolbar",{attrs:{flat:""}},[n("v-toolbar-title",[e._v("Unique locations")]),n("v-divider",{staticClass:"mx-4",attrs:{inset:"",vertical:""}}),n("v-spacer"),n("v-btn",{staticClass:"mb-2",attrs:{color:"primary",dark:""},on:{click:e.getUniqueLocations}},[e._v("Get")])],1)]},proxy:!0}])})],1)}),[],!1,null,null,null);t.default=component.exports;y()(component,{VAlert:C.a,VBtn:I.a,VCard:k.a,VCardActions:D.a,VCardText:D.b,VCardTitle:D.c,VCol:_.a,VContainer:P.a,VDataTable:w.a,VDialog:$.a,VDivider:B.a,VIcon:z.a,VPagination:O.a,VRow:R.a,VSelect:A.a,VSpacer:S.a,VTextField:j.a,VToolbar:V.a,VToolbarTitle:U.a})}}]);