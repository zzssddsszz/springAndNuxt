<template>
  <b-row>
    <b-colxx xxs="12">
      <piaf-breadcrumb :heading="'제품'"/>
      <div class="separator mb-5"></div>
    </b-colxx>
    <b-colxx xxs="12">
      <b-card class="mb-4" :title="'제품사진'">
        <image-sortable
          :images="addedMainImages"
          @changeIndex="changeIndex"
          @deleteMainImage="deleteMainImage"
        ></image-sortable>
        <b-button v-b-modal.modalbasic variant="outline-primary">{{ '사진 추가' }}</b-button>
        <b-modal id="modalbasic" ref="modalbasic" :title="'사진 업로드'">
          <vue-dropzone ref="myVueDropzone" id="dropzone"
                        @vdropzone-success="vsuccess"
                        @vdropzone-error="verror"
                        :options="dropzoneOptions"
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
          </b-row>
          <tiny-editor
            v-if="loaded"
            @changeContent="changeContent"
            :initValue="newItem.content"
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
import _ from 'lodash';

export default {
  beforeMount() {
    let id = this.$route.params.id;
    if (id) {
      axios.get(`/products/${id}`).then(res => {
        let value = res.data.data;
        this.newItem.id = value.id;
        this.newItem.name = value.name;
        this.newItem.content = value.content;
        if (value.attachmentList) {
          for (let img of value.attachmentList) {
            this.addedMainImages.push(img)
          }
        }
        this.editMode = true;
        this.loaded = true;
      })
    } else {
      this.editMode = false;
      this.loaded = true;
    }

  },
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
      changeImg: true,
      editMode: false,
      loaded: false,
      addedMainImages: [],
      newItem: {
        id: "",
        name: "",
        mainImages: [],
        content: ""
      },
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
          "Accept": "application/json"
        },
      }
    };
  },
  methods: {
    changeContent(content) {
      this.newItem.content = content;
    },
    deleteMainImage(id) {
      let index = this.addedMainImages.findIndex(element => (element.id === id));
      this.addedMainImages.splice(index, 1);
    },
    formSubmit() {
      this.newItem.mainImages.splice(0, this.newItem.mainImages.length)
      this.addedMainImages.map((element, index) => this.newItem.mainImages.push(element.id))

      if (!this.editMode) {
        axios.post("/products", this.newItem).then(({data}) => {
          console.log("push")
          this.$router.push("list")
        }).catch(error => {
          console.log(error)
        })
      } else {
        axios.put(`/products/${this.$route.params.id}`, this.newItem).then(({data}) => {
          console.log("push")
          this.$router.push("../list")
        }).catch(error => {
          // this.errorMessage = error.message
          console.log(error)
        })
      }

    },
    changeIndex(oldIndex, newIndex) {
      let temp = this.addedMainImages[oldIndex];
      this.addedMainImages[oldIndex] = this.addedMainImages[newIndex];
      this.addedMainImages[newIndex] = temp;
    },
    vsuccess(file, response) {
      this.success = true
      this.addedMainImages.push(response.data)
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
    },

  }
};
</script>
