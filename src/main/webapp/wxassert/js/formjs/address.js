function getUserAddressList(){

}

var cityPicker3 = new mui.PopPicker({
    layer: 3
});
function getRegionData(){
    // cityPicker3.setData(cityData3);
}


function addAddressB(){
    var addressCount =$('#addressCount').text();
    var linkMan= $('#c-linkman').val();
    var phone= $('#c-phone').val();
    var adminCode= $('#c-adminCode').val();
    var address= $('#c-address').val();
    if(null==linkMan||""==linkMan){
        return false;
    }
    if(!(/^1[3|4|5|7|8]\d{9}$/).test(phone)){
        return false;
    }
    if(null==adminCode||""==adminCode){
        return false;
    }
    if(null==address||""==address){
        return false;
    }
}


function updateDefaultAddress(rowid){

}

function editAddress(rowId){
}


function doUpdateAddress(){
    var rowId= $('#e-rowid').val();
    var linkman= $('#e-linkman').val();
    var phone=$('#e-phone').val();
    var adminCodeName=$('#e-adminCodeName').val();
    var adminCode=$('#e-adminCode').val();
    var address= $('#e-address').val();
    if (isEmpty(linkman)) {
        return false;
    }
    if(!(/^1[3|4|5|7|8]\d{9}$/).test(phone)){
        return false;
    }
    if(null==adminCode||""==adminCode){
        return false;
    }
    if (isEmpty(address)) {
        return false;
    }


}

function doDeleteAddress(rowId,isdefault){
}


function isEmpty(obj) {
    if (!obj) {
        return true;
    }
    if (obj == 'undefined') {
        return true;
    }
    if (obj.length <= 0) {
        return true;
    }
    if (obj == '') {
        return true;
    }
    return false;
}