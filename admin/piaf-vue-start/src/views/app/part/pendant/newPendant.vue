<template>
  <b-row>
    <b-colxx xxs="12">
      <b-card class="mb-4" :title="'제품사진'">
        <image-sortable
          :images="mainImages"
          @changeIndex="changeIndex"
          @deleteMainImage="deleteMainImage"
        ></image-sortable>
        <b-button v-b-modal.modalbasic variant="outline-primary">{{ '사진 추가' }}</b-button>
        <b-modal id="modalbasic" ref="modalbasic" :title="'사진 업로드'">
          <vue-dropzone ref="myVueDropzone" id="dropzone"
                        @vdropzone-success="vsuccess"
                        @vdropzone-error="verror"
                        :options="dropzoneOptions"
                        :duplicateCheck="true"
          ></vue-dropzone>
        </b-modal>
      </b-card>


      <b-card class="mb-4" :title="'데이터'">
        <b-form @submit.prevent="formSubmit">
          <b-row>
            <b-colxx sm="4">
              <label class="form-group has-float-label">
                <input type="text" class="form-control" v-model="newItem.name"/>
                <span>이름</span>
              </label>
            </b-colxx>
            <b-colxx sm="4">
              <label class="form-group has-float-label">
                <input type="number" class="form-control" v-model="newItem.buyPrice"/>
                <span>구매가</span>
              </label>
            </b-colxx>
            <b-colxx sm="4">
              <label class="form-group has-float-label">
                <input type="number" class="form-control" v-model="newItem.stock"/>
                <span>수량</span>
              </label>
            </b-colxx>
            <b-colxx sm="4">
              <label class="form-group has-float-label">
                <v-select
                  v-model="newItem.color"
                  :options="color"
                />
                <span>색상</span>
              </label>
            </b-colxx>
            <b-colxx sm="4">
              <div class="form-group has-float-label">
                <input-tag v-model="newItem.tags"></input-tag>
                <span>태그</span>
              </div>
            </b-colxx>
            <!--            <b-colxx sm="4">
                          <div class="form-group has-float-label">
                            <v-select
                              v-model="topLabelsOverLineForm.select"
                              :options="selectData"
                              :dir="direction"
                            />
                            <span>{{ $t('forms.state') }}</span>
                          </div>
                        </b-colxx>-->
          </b-row>
          <tiny-editor
            @changeContent="changeContent"
            @addImage="contentAddImage"
          ></tiny-editor>
          <b-button type="submit" variant="primary" class="mt-4">{{ $t('forms.submit') }}</b-button>
        </b-form>
      </b-card>
    </b-colxx>
  </b-row>
</template>
<script>
import vSelect from "vue-select";
import "vue-select/dist/vue-select.css";
import Datepicker from "vuejs-datepicker";
import InputTag from "@/components/Form/InputTag";
import axios from "axios";
import VueDropzone from "vue2-dropzone";
import ImageSortable from "@/components/Form/ImageSortable";
import TinyEditor from "@/components/Editor/TinyEditor";

export default {
  components: {
    "input-tag": InputTag,
    "v-select": vSelect,
    "datepicker": Datepicker,
    "vue-dropzone": VueDropzone,
    "tiny-editor": TinyEditor,
    "image-sortable": ImageSortable
  },
  data() {
    return {
      mainImages: [],
      newItem: {
        name: "",
        mountingType: "",
        material: "",
        color: "",
        buyPrice: "",
        stock: "",
        tags: [],
        mainImageIndex: [],
        contentImage:[],
        content: ""
      },
      color: ["무도금", "핑크골드", "화이트골드"],
      errorMessage: '',

      topLabelsOverLineForm: {

        date: null,
        select: "",
        checked: false
      },
      dropzoneOptions: {
        url: "/api/image",
        thumbnailHeight: 160,
        maxFilesize: 30,
        previewTemplate: this.dropzoneTemplate(),
        headers: {
          "My-Awesome-Header": "header value"
        },
      }
    };
  },
  watch: {
    mainImages: function () {
      this.newItem.mainImageIndex.splice(0, this.newItem.mainImageIndex.length)
      this.mainImages.map((element,index) => this.newItem.mainImageIndex.push(element.id))
    }
  },
  methods: {
    changeContent(content){
      this.newItem.content=content;
    },
    contentAddImage(id){
      this.newItem.contentImage.push(id);
    },
    deleteMainImage(id){
      let index = this.mainImages.findIndex(element => (element.id===id));
      this.mainImages.splice(index,1);
    },
    formSubmit() {
      console.log(console.log(JSON.stringify(this.newItem)));
      axios.post("/pendants", this.newItem).then(({data}) => {
        this.$emit('added')
        this.hideModal('modalright')
      }).catch(error => {
        this.errorMessage = error.message
      })
    },
    addNewItem() {
      axios.post("/pendants", this.newItem).then(({data}) => {
        this.$emit('added')
        this.hideModal('modalright')
      }).catch(error => {
        this.errorMessage = error.message
      })
    },
    changeIndex(oldIndex, newIndex) {
      let temp = this.mainImages[oldIndex];
      this.mainImages[oldIndex] = this.mainImages[newIndex];
      this.mainImages[newIndex] = temp;
    },

    vsuccess(file, response) {
      this.success = true
      this.mainImages.push(response.data)
    },
    verror(file, error, xhr) {
      const elements = document.querySelectorAll(".dz-preview");
      for (const element of elements) {
        const filename = element.querySelectorAll("span[data-dz-name]")[0].textContent;
        const errorMessage = element.querySelectorAll("span[data-dz-errormessage]")[0];
        if (filename === file.name) {
          console.log(error)
          errorMessage.textContent = error.error.message;
        }
      }
      // $('.dz-error-message span').text(parse.message);
      // console.log(xhr);
      // window.toastr.error(file.upload.filename, 'Event : vdropzone-error - ' + file.status)
    },
    dropzoneTemplate() {
      return `<div class="dz-preview dz-file-preview mb-3">
                  <div class="d-flex flex-row "> <div class="p-0 w-30 position-relative">
                      <div class="dz-error-mark"><span><i></i>  </span></div>
                      <div class="dz-success-mark"><span><i></i></span></div>
                      <div class="preview-container">
                        <img data-dz-thumbnail class="img-thumbnail border-0" />
                        <i class="simple-icon-doc preview-icon"></i>
                      </div>
                  </div>
                  <div class="pl-3 pt-2 pr-2 pb-1 w-70 dz-details position-relative">
                    <div> <span data-dz-name /> </div>
                    <div class="text-primary text-extra-small" data-dz-size /> </div>
                    <div class="dz-progress"><span class="dz-upload" data-dz-uploadprogress></span></div>
                    <div class="dz-error-message"><span data-dz-errormessage></span></div>
                  </div>
                  <a href="#" class="remove" data-dz-remove> <i class="glyph-icon simple-icon-trash"></i> </a>
                </div>
        `;
    }
  }
};
</script>
