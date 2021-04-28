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
          @addToEditor="addToEditor"
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

    <b-button class="fixed-bottom" v-b-toggle.sidebar>사진토글 버튼</b-button>

    <b-sidebar id="sidebar" title="Sidebar" shadow>
      <b-btn @click="addToEditor(null,'tableAdd')">테이블 추가</b-btn>
      <b-card v-for="i in this.addedMainImages" v-bind:key="i.id" class="col-10 m-4">
        <img :src="toThumbnail(i.location)" class="card-img" :alt="i.title" style="width: 100%"/>
        <div>
          <b-card-text v-text="i.fileName" style="height: 1rem"></b-card-text>
          <b-badge pill class="position-absolute badge-bottom-left" href="#" variant="secondary"
                   @click="addToEditor(i,'img')">O
          </b-badge>
        </div>
      </b-card>
    </b-sidebar>
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
      }).catch((error) => {
          this.loaded = true;
          console.log("상품 정보 얻기 실패했습니다.")
        }
      )
    } else {
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
  computed: {
    toThumbnail() {
      return (image) => image.replace(".jpg", ".thumbnail.jpg")
    }
  },
  methods: {
    addToEditor(img, type) {
      let activeEditor = tinyMCE.activeEditor;

      if (type === 'img') {
        activeEditor.selection.setNode(activeEditor.dom.create('img', {
          src: img.location,
          title: img.fileName,
          style: "width:100%"
        }));
        activeEditor.selection.setNode(activeEditor.dom.create('p'));

      }

      if (type === 'tableAdd'){
        let table = activeEditor.dom.create('table', {
          style: "border-collapse: collapse; width: 100%;",
          border: "0",
          cellpadding: "0"
        })
        let tbody = table.appendChild(document.createElement("tbody"));
        let tr = tbody.appendChild(document.createElement("tr"));
        let td1 = document.createElement("td");
        let td2 = document.createElement("td");
        td1.style.width = "50%";
        td2.style.width = "50%";
        tr.appendChild(td1);
        tr.appendChild(td2);

        activeEditor.selection.setNode(table);
      }

    },
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
