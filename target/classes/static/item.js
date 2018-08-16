



function viewAddItem(){
    window.location.href='addItem.html';
};

function viewSoldItem(){
    window.location.href='adminSoldItems.html';
};


var app = angular.module('itemsFusepong', []);

app.controller('myCtrl', function($scope,$http,$q) {
    
   function Item(){
        this.id = 0;
        this.nombre = "";
        this.descripcion = "";
        this.valor = 0;
        this.iva = 0.0;
        this.envio = "";
        this.cantidad = 0;
        this.foto = null;
    }
    
    $scope.istrue = false;
    
    
    function getItems(){
        var deferred = $q.defer();
        var itemsList;
        var url = '/api/items';
        $http.get(url).then(function success(response){
            itemsList = response.data;
            deferred.resolve(itemsList);
        },function fail(){
            deferred.reject();
        });
        return deferred.promise;
    }
    
    function getSoldItems(){
        var deferred = $q.defer();
        var soldItemsList;
        var url = '/api/sold/items';
        $http.get(url).then(function success(response){
            soldItemsList = response.data;
            deferred.resolve(soldItemsList);
        },function fail(){
            deferred.reject();
        });
        return deferred.promise;
    }
    
    function deleteItemService(itemId){
        var deferred = $q.defer();
        var url = '/api/item/'+itemId;
        $http.delete(url).then(function success(){
            deferred.resolve();
        }, function fail(){
            deferred.reject();
        });
        return deferred.promise;
    }
    
    function saveItemService(itemImage){
        var deferred = $q.defer();
        var url = '/api/item';
        $http.post(url,itemImage,{
            transformRequest: angular.identity,
            headers: {'Content-Type': undefined}
        }).then(function success(){
            deferred.resolve();
        }, function fail(){
            deferred.reject();
        });
        return deferred.promise;
    }
    
    function buyItemService(item){
        var deferred = $q.defer();
        var url = '/api/compra/item';
        $http.put(url,item).then(function success(){
            deferred.resolve();
        }, function fail(){
            deferred.reject();
        });
        return deferred.promise;
    }
    
    function updateItemService(item){
        var deferred = $q.defer();
        var url = '/api/item';
        $http.put(url,item,{
            transformRequest: angular.identity,
            headers: {'Content-Type': undefined}
        }).then(function success(){
            deferred.resolve();
        },function fail(){
            deferred.reject();
        });
        return deferred.promise;
    }
    
    $scope.deleteItem = function(itemId){
        deleteItemService(itemId).then(function(){
            console.log("Se eliminó el item");
        });
        window.location.reload();
    };
    
    $scope.buyItem = function(item){
        item.envio = sessionStorage.usuario;
        console.log(sessionStorage.usuario);
        buyItemService(item).then(function(){
            console.log("Item comprado");
        });
        window.location.reload();
    };
    
    
    $scope.addItem = function(){
        nombre = $("#nombre").val();
        descripcion = $("#descripcion").val();
        valor = $("#valor").val();
        iva = $("#iva").val();
        envio = $("#envio").val();
        cantidad = $("#cantidad").val();
        item = new Item();
        item.nombre = nombre;
        item.descripcion = descripcion;
        item.valor = valor;
        item.iva =iva;
        item.envio = envio;
        item.cantidad = cantidad;
        var formData = new FormData();
        formData.append('item', new Blob([JSON.stringify(item)],{type: "application/json"}));
        //formData.append('item',item);
        formData.append('file',$scope.imagen);
        saveItemService(formData).then(function(){
            console.log("Se agregó el item");
            console.log($scope.imagen);
        });
         window.location.href='adminItems.html'; 
    };
    
    getItems().then(function(result){
        console.log(result);
       $scope.items = result;
    });
    
    getSoldItems().then(function(result){
       $scope.soldItems = result; 
    });
    
    
    $scope.updateItemView = function(item){
        $scope.editedItem = item;
        $scope.istrue = true;
    };
    
    $scope.closepopup=function(){
         $scope.istrue = false;

     };
    
    $scope.imagen;
    
    $scope.uploadFile = function(files) {
        if (files && files[0]) {
            var reader = new FileReader();
            reader.onload = function (e) {
                $('#imageItem').attr('src', e.target.result);
            };
        reader.readAsDataURL(files[0]);
        }
        console.log(files[0]);
        $scope.imagen = files[0];
    };
    
    $scope.updateItem = function(){
        item = new Item();
        item.id = $scope.editedItem.id;
        nombre = $("#nombre").val();
        descripcion = $("#descripcion").val();
        valor = $("#valor").val();
        iva = $("#iva").val();
        cantidad = $("#cantidad").val();
        if($scope.editedItem.nombre != nombre){
            item.nombre = nombre;
        }else{
            item.nombre = $scope.editedItem.nombre;
        }if($scope.editedItem.descripcion != descripcion){
            item.descripcion = descripcion;
        }else{
            item.descripcion = $scope.editedItem.descripcion;
        }if($scope.editedItem.valor != valor){
            item.valor = valor;
        }else{
             item.valor = $scope.editedItem.valor;
        }if($scope.editedItem.iva != iva){
            item.iva = iva;
        }else{
            item.iva = $scope.editedItem.iva;
        }if($scope.editedItem.cantidad != cantidad){
            item.cantidad = cantidad;
        }else{
            item.cantidad = $scope.editedItem.cantidad;
        }
        var formData = new FormData();
        formData.append('item', new Blob([JSON.stringify(item)],{type: "application/json"}));
        formData.append('file',$scope.imagen);
        
        updateItemService(formData).then(function(){
            console.log("Actualizó el item");
        });
        $scope.istrue = false;
    };
    
});



