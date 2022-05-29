<template>
    <form id="order-from">
    <div id = "demo" class="row">
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
    </div>
    
    <div class="row">
        <div class="col-12 form-group">
            <label class="col-form-label col-form-label-lg"> Full name <span class="text-danger">* </span></label>
            <input type="text" class="from-controll">
        </div>
    </div>
    </form>
</template>

<script>
    export default{
        name:'OrderForm',
        data() {
            return {
                checkedList: [],
                myDragList: [
                    { name: "item" },
                    { name: "item2" },
                    { name: "item" }
                ]
            };
        },
        methods: {
            handleDragSelectorChange(list) {
            console.log("list", list);
        }
    }
};
</script>

<style>
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