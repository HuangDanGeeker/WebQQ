var myDB={
        name:'LocalTalkContent',
        version:1,
        db:null,
    };
var talkContent = {
		from:"",
		to: "",
		content: "",
		timestamp:null
};

    var INDEXDB = {
        indexedDB:window.indexedDB||window.webkitindexedDB,
        IDBKeyRange:window.IDBKeyRange || window.webkitIDBKeyRange,//键范围
        openDB:function(dbname,dbversion,tableName,callback){
            //建立或打开数据库，建立对象存储空间(ObjectStore)
            var self = this;
            var    version = dbversion || 1;
            var request = self.indexedDB.open(dbname,version);
            request.onerror = function(e){
                console.log(e.currentTarget.error.message);
            };
            request.onsuccess = function(e){
                myDB.db = e.target.result;
                console.log('成功建立并打开数据库:'+myDB.name+' version'+dbversion);
            };
            request.onupgradeneeded=function(e){
            	console.log("成功upgrade");
                var db=e.target.result,transaction= e.target.transaction,store;
                for(var i = 0; i < tableName.length; i++){
                	if(db.objectStoreNames.contains(tableName[i])){
	                    //清空数据库
                		store = db.transaction(storename,'readwrite').objectStore(storename);
                        store.clear();
	                    continue;
	                }
	                if(!db.objectStoreNames.contains(tableName[i])){
	                    //没有该对象空间时创建该对象空间
	                    store = db.createObjectStore(tableName[i],{autoIncrement:true});
	                    console.log('成功建立对象存储空间：'+tableName[i]);
	                }
                }
            }


        },
        deletedb:function(dbname){
            //删除数据库
            var self = this;
            self.indexedDB.deleteDatabase(dbname);
            console.log(dbname+'数据库已删除')
        },
        closeDB:function(db){
            //关闭数据库
            db.close();
            console.log('数据库已关闭')
        },
        addData:function(db,storename,data){
            //添加数据，重复添加会报错
            var store = store = db.transaction(storename,'readwrite').objectStore(storename),request;
            request = store.add(data);
            request.onerror = function(){
                console.error('add添加数据库中已有该数据')
            };
            request.onsuccess = function(){
                console.log('add添加数据已存入数据库')
            };

        },
        putData:function(db,storename,data){
            //添加数据，重复添加会更新原有数据
            var store = store = db.transaction(storename,'readwrite').objectStore(storename),request;
            for(var i = 0 ; i < data.length;i++){
                request = store.put(data[i]);
                request.onerror = function(){
                    console.error('put添加数据库中已有该数据')
                };
                request.onsuccess = function(){
                    console.log('put添加数据已存入数据库')
                };
            }
        },
        uploadAllData:function(db, storename){
            //根据存储空间的键找到对应数据
            var store = db.transaction(storename,'readwrite').objectStore(storename);
            var request = store.getAll();
            request.onerror = function(){
                console.error('getDataByKey error');
            };
            request.onsuccess = function(e){
//            	var str = JSON.stringify([{name:"sss"},{name:"mmm"}]);
                var result = e.target.result;
                console.log('查找数据成功')
                console.log(result);
                var result_str = JSON.stringify(result);
                console.log(result_str);
                $.ajax({
            		url:"http://localhost:8080/SpringMVC/uploadHistory",
                    dataType:'json',
                    data:result_str,
                    contentType:"application/json",
                    processData: true, 
                    type:'POST',
            		error:function(XMLHttpRequest, textStatus, errorThrown){}
                });
                
            };
        },
        
        clearData:function(db,storename){
            //删除存储空间全部记录
            var store = db.transaction(storename,'readwrite').objectStore(storename);
            store.clear();
            console.log('已删除存储空间'+storename+'全部记录');
        },

    }

    




    //api
	//INDEXDB.addData(myDB.db,"mmm",talkContent);
	//INDEXDB.closeDB()

  