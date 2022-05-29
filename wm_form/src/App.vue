<template>
  <div id="app" class="h-100">
    <div class="container-fluid h-100">
      <div class="row h-100">
        <div class="col-md-9 h-100 d-flex justify-content-center allign-items-center">
          <div class="col-md-8 rounded px-5 py-4 shadow bg-white text-left">
            <form id="order-from" v-on:submit.prevent = "submit">
    <!-- <div id = "demo" class="row">
        <drag-selector v-model="checkedList"
                       @change="handleDragSelectorChange" class="drag-selector">
            <drag-selector-item v-for="(item, index) in myDragList"
                                :value="item"
                                :key="index" class="drag-selector__item">
                {{ item.name }}
            </drag-selector-item>
        </drag-selector>
        <div class="result">
        <div>[<span v-show="!checkedList.length">]</span></div>
        <div v-for="(item,index) in checkedList" :key="`result${index}`">
            &nbsp;&nbsp;&nbsp;&nbsp;{{ item }},
        </div>
        <div v-show="checkedList.length">]</div>
    </div>
    </div> -->
    
    <div class="row h-100">
        <div class="col-12 form-group">
            <label class="col-form-label col-form-label-lg"> Address <span class="text-danger">* </span></label>
            <input type="text" v-model.trim = "address" class="from-controll is-invalid from-controll-lg">
            
        </div>
    </div>
    <div class="row h-100">
        <div class="col-12 form-group">
            <label class="col-form-label col-form-label-lg"> Phone <span class="text-danger">* </span></label>
            <input type="text" v-model.trim = "phoneNumber" class="from-controll">
        </div>
    </div>
    <div class="row h-100">
        <div class="col-12 form-group">
            <label class="col-form-label col-form-label-lg"> Select Watermellon <span class="text-danger">* </span></label>
            <Select class="form_control from-control-lg" v-model="watermellon" id="123" multiple >
              <option value="">Select</option>
              <option :id="c.id" :value = "c.row_collumn" :key="c.id" v-for = "c in info" >{{c.row_collumn}}</option>
            </Select>
        </div>
    </div>
    <div class="row h-100">
        <div class="col-12 form-group">
            <label class="col-form-label col-form-label-lg" > 
              <DatePicker v-model="date" @update:modelValue="handleDate"> </DatePicker></label>
            <!-- //Full name <span class="text-danger">* </span> -->
        </div>
    </div>
    
    <div class="row h-100">
        <div class="col-12 form-group">
            <label class="col-form-label col-form-label-lg"> Slice? <span class="text-danger">* </span></label>
            <input type="checkbox" id="checkbox" v-model="checked">
            <label for="checkbox">{{ checked }}</label>
        </div>
    </div>
    <div class="col-12 form-group text-center">
      <button class="btn btn-vue btn-lg col-4">Order</button>
    </div>
    </form>
          </div>
        </div> 
      </div>
    </div>
  </div>
</template>

<script>
//import HelloWorld from './components/HelloWorld.vue'
//import OrderForm from './components/OrderForm.vue';
import { ref } from 'vue';
import {required} from 'vuelidate/lib/validators';
import axios from 'axios';
import DatePicker from '@vuepic/vue-datepicker';
    import '@vuepic/vue-datepicker/dist/main.css'

export default {
  name: 'App',
  components: {
    DatePicker
},
setup() {
        const date = ref();
        // Make sure to check modelData type here https://vue3datepicker.com/api/props/#modelvalue
        const handleDate = (modelData) => {
          date.value = modelData;
          // do something else with the data
          console.log("dfjkdhf");
          return modelData;
          
        }
        
        return {
          date,
          handleDate
        } 
    },
data() {
        return {
            address: "",
            phoneNumber: "",
            watermellon:"",
            sliced: "",
            info: [],
            watermellonList: []
        };
        },
        validations :{
            address: {required}
        },
        methods: {
            validationStatus: function(validation){
                return typeof validation !="undefined" ? validation.$error : false;
            },

          submit: function() {
            var selected = [];
            for(var option of document.getElementById('123').options){
              if(option.selected){
                selected.push(option.id)
              }
            }
            
             axios.patch('http://wmapi.com/watermellons/'+ selected[0], {
               "address": this.address,
               "phone_number": this.phoneNumber,
               "delivery_date": this.handleDate.value
             }).then(response=>{
               console.log(response.data)
             });

            

          },
            handleDragSelectorChange(list) {
            console.log("list", list);
        }
},
async created() {
    axios.get('http://wmapi.com/watermellons', {headers : {
                'content-type': 'json/application'
}} )
      .then(response =>{
        this.info = response.data;
        console.log(response.data)
        });
      
  }
}
</script>

<style>
.btn-vue{
  background-color: #03A9F4;
  color: #B3E5FC;
  font-weight: bold;
}
#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50; 
}
#demo {
            width: 740px;
            height: 300px;
            margin-left: auto;
            margin-right: auto;
            background: #B3E5FC;
            border-radius: 4px;
        }

        .drag-selector {
            display: flex;
            align-content: flex-start;
            flex-wrap: wrap;
            padding: 10px;
            margin-top: 20px;
        }

        .drag-selector__item {
            width: 100px;
            height: 100px;
            margin: 10px;
            border-radius: 4px;
            display: flex;
            align-items: center;
            justify-content: center;
            background-color: #03A9F4;
            color: white;
        }

        .drag-selector__item.selected {
            background-color: #FF4081;
        }
</style>
