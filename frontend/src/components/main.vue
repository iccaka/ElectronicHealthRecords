<template>

  <v-app id="teal">
    <v-layout class="scrollbar">
      <v-navigation-drawer v-model="drawer"
                           fixed
                           app
                           width="180">

        <v-list dense>
          <v-list-tile class="profile" v-on:click.prevent="open_user_profil">
            <v-list-tile-action>
              <v-icon>person</v-icon>
            </v-list-tile-action>
            <v-list-tile-content>
              <v-list-tile-title>Profile</v-list-tile-title>
            </v-list-tile-content>
          </v-list-tile>
          <v-list>
            <v-list-group>
              <v-list-tile slot="activator">
                <v-list-tile-action>
                  <v-icon class="settings">settings</v-icon>
                </v-list-tile-action>
                <v-list-tile-title>Settings</v-list-tile-title>
              </v-list-tile>
              <v-list-tile @click="open_password_change">
                <v-list-tile-content>
                  <v-list-tile-title>Password</v-list-tile-title>
                </v-list-tile-content>
              </v-list-tile>
              <br>
              <!--open add parent-->
              <v-list-tile @click="open_add_parent">
                <v-list-tile-content>
                  <v-list-tile-title>Amka</v-list-tile-title>
                </v-list-tile-content>
              </v-list-tile>
            </v-list-group>
          </v-list>
          <!--open suggested articles-->
          <v-list-tile @click="open_suggested_articles">
            <v-list-tile-action>
              <v-icon class="articles">receipt</v-icon>
            </v-list-tile-action>
            <v-list-tile-content>
              <v-list-tile-title>Articles</v-list-tile-title>
            </v-list-tile-content>
          </v-list-tile>
          <!--open about dialog-->
          <v-list-tile v-on:click.prevent="open_about_dialog">
            <v-list-tile-action>
              <v-icon class="about">info</v-icon>
            </v-list-tile-action>
            <v-list-tile-content>
              <v-list-tile-title>About</v-list-tile-title>
            </v-list-tile-content>
          </v-list-tile>
        </v-list>
        <!--Top bar-->
      </v-navigation-drawer>
      <v-toolbar color="teal" dark fixed app>
        <v-toolbar-side-icon @click.stop="drawer = !drawer"></v-toolbar-side-icon>
        <v-toolbar-title>Medical Application</v-toolbar-title>
      </v-toolbar>
      <v-content>
        <div class="welc">
          <h2>{{ msg }}</h2>
          <hr>
          <v-container grid-list-md text-xs-left>
            <v-layout row wrap>
              <v-flex xs12>
                <v-card dark color="white">
                  <v-card-text class="px-15">
                    Through the Medical Application you can get information about medical news that interests you as
                    well as your medical condition based on your history and the background of your relatives.
                  </v-card-text>
                </v-card>
              </v-flex>
            </v-layout>
          </v-container>
          <hr>
          <v-container grid-list-md text-xs-center>
            <v-layout row wrap>
              <v-flex xs12>
                <v-card dark color="white">
                  <div>
                    <v-card-text class="top_news">Top News</v-card-text>
                  </div>
                  <!--Top news with articles-->
                  <v-card-text class="px-15">
                    <v-layout row wrap text-xs-left v-for="main_article in main_articles">
                      <v-flex xs12>
                        <v-card class="cards">
                          <v-layout row>
                            <v-flex xs6>
                              <img v-if="!!main_article.image_url" v-bind:src="host+main_article.image_url" height="100"
                                   width="100">
                            </v-flex>
                            <v-flex xs6>
                              <v-card-text class="article_title">{{ main_article.title}}</v-card-text>
                              <v-btn flat="flat" color="primary" v-model="main_article.hasmain_article"
                                     v-on:click.prevent="read_one_article(main_article)">Read
                              </v-btn>
                            </v-flex>
                          </v-layout>
                          <pre>{{main_article.hasmain_article}}</pre>
                        </v-card>
                      </v-flex>

                    </v-layout>
                    <!--see more articles-->
                    <v-btn v-on:click.prevent="open_all_articles" flat="flat" color="primary">See more</v-btn>
                  </v-card-text>
                </v-card>
              </v-flex>
            </v-layout>
          </v-container>
          <hr>
        </div>
        <!--start user buttons-->
        <v-container fluid fill-height>
          <v-layout justify-center align-center>
          </v-layout>
        </v-container>
      </v-content>
      <v-speed-dial class="button"
                    v-model="fab"
                    :top="top"
                    :bottom="bottom"
                    :right="right"
                    :left="left"
                    :direction="direction"
                    :open-on-hover="hover"
                    :transition="transition"
                    fixed>
        <v-btn
          slot="activator"
          color="teal"
          dark
          fab
          hover
          v-model="fab">
          <v-icon>account_circle</v-icon>
          <v-icon>close</v-icon>
        </v-btn>
        <v-btn v-on:click.prevent="diseasesHistory"
               fab
               dark
               small
               color="green">
          <v-icon>perm_identity</v-icon>
        </v-btn>
        <v-btn v-on:click.prevent="add_diseases"
               fab
               dark
               small
               color="indigo">
          <v-icon>add</v-icon>
        </v-btn>
        <v-btn v-on:click.prevent="open_contact"
               fab
               dark
               small
               color="indigo">
          <v-icon>email</v-icon>
        </v-btn>
        <v-btn v-on:click.prevent="logout"
               fab
               dark
               small
               color="red">
          <v-icon>exit_to_app</v-icon>
        </v-btn>
      </v-speed-dial>
      <!--End user buttons-->

      <!-- start dialog Diseases History -->
      <v-dialog
        v-model="dialog"
        fullscreen
        transition="dialog-bottom-transition"
        :overlay="false"
        scrollable
      >
        <v-card tile>
          <v-toolbar card dark color="teal">
            <v-btn icon @click.native="dialog = false" dark>
              <v-icon>close</v-icon>
            </v-btn>
            <v-toolbar-title>Diseases History</v-toolbar-title>
          </v-toolbar>
          <h2>Diseases</h2><br>
          <hr>
          <v-card-text class="px-15">
            <v-layout row wrap text-xs-center v-for="dise in dises">
              <v-flex xs12>
                <v-card class="cards">
                  <v-card-text class="px-15">{{ dise.name }}</v-card-text>
                </v-card>
                <hr>
              </v-flex>
            </v-layout>
          </v-card-text>
          <hr>
        </v-card>
        <v-footer color="teal" app>
          <span class="white--text">&copy; 2018</span>
        </v-footer>
      </v-dialog>
      <!-- Enad dialog Diseases History -->

      <!--Start All articles dialog-->
      <v-dialog
        v-model="articles_dialog"
        fullscreen
        transition="dialog-bottom-transition"
        :overlay="false"
        scrollable
      >
        <v-card tile>
          <v-toolbar card dark color="teal">
            <v-btn icon @click.native="articles_dialog = false" dark>
              <v-icon>close</v-icon>
            </v-btn>
            <v-toolbar-title>All Articles</v-toolbar-title>
          </v-toolbar>
          <div class="search">
            <v-text-field class="mx-0"
                          prepend-icon="search"
                          label="Search"
                          solo-inverted
                          v-model="search"
                          flat
            ></v-text-field>
          </div>
          <hr>
          <v-card-text class="px-15">
            <v-layout row wrap text-xs-left v-for="article in filteredArticles">
              <v-flex xs12>
                <v-card class="cards">
                  <v-layout row>
                    <v-flex xs6>
                      <img v-if="!!article.image_url" v-bind:src="host+article.image_url" height="100" width="100">

                    </v-flex>
                    <v-flex xs6>
                      <v-card-text class="article_title">{{ article.title}}</v-card-text>
                      <v-btn flat="flat" color="primary" v-model="article.hasArticle"
                             v-on:click.prevent="read_one_article(article)">Read
                      </v-btn>
                    </v-flex>
                  </v-layout>
                  <pre>{{article.hasArticle}}</pre>
                </v-card>
                <hr>
              </v-flex>
            </v-layout>
          </v-card-text>
          <hr>
        </v-card>
        <v-footer color="teal" app>
          <span class="white--text">&copy; 2018</span>
        </v-footer>
      </v-dialog>
      <!--End All articles dialog-->

      <!--Stast suggested dialog-->
      <v-dialog
        v-model="suggested_articles_flag"
        fullscreen
        transition="dialog-bottom-transition"
        :overlay="false"
        scrollable
      >
        <v-card tile>
          <v-toolbar card dark color="teal">
            <v-btn icon @click.native="suggested_articles_flag = false" dark>
              <v-icon>close</v-icon>
            </v-btn>
            <v-toolbar-title>Suggested Articles</v-toolbar-title>
          </v-toolbar>
          <hr>
          <v-card-text class="px-15">
            <v-layout row wrap text-xs-left v-for="sugg_article in sugg_articles">
              <v-flex xs12>

                <v-card class="cards">
                  <v-layout row>
                    <v-flex xs6>
                      <img v-if="!!sugg_article.image_url" v-bind:src="host+sugg_article.image_url" height="100"
                           width="100">
                    </v-flex>
                    <v-flex xs6>
                      <v-card-text class="article_title">{{ sugg_article.title}}</v-card-text>
                      <v-btn flat="flat" color="primary" v-model="sugg_article.hasArticle"
                             v-on:click.prevent="read_one_article(sugg_article)">Read
                      </v-btn>
                    </v-flex>
                  </v-layout>

                  <pre>{{sugg_article.hasArticle}}</pre>
                </v-card>

                <hr>
              </v-flex>
            </v-layout>
          </v-card-text>
          <hr>
        </v-card>

        <v-footer color="teal" app>
          <span class="white--text">&copy; 2018</span>
        </v-footer>
      </v-dialog>
      <!--End suggested dialog-->

      <!-- Start Add diseases dialog -->
      <v-dialog
        v-model="add_diseases_dialog"
        fullscreen
        transition="dialog-bottom-transition"
        :overlay="false"
        scrollable
      >
        <v-card tile>
          <v-toolbar card dark color="teal">
            <v-btn icon @click.native="add_diseases_dialog = false" dark>
              <v-icon>close</v-icon>
            </v-btn>
            <v-toolbar-title>Add Diseases</v-toolbar-title>
            <v-spacer></v-spacer>
            <v-toolbar-items>
              <v-btn dark flat="flat" @click="save_diseases_user(alldiseases)">Save</v-btn>
            </v-toolbar-items>
          </v-toolbar>
          <h2>Diseases</h2><br>
          <hr>
          <v-card-text class="px-15">
            <v-layout row wrap text-xs-left v-for="alldisease in alldiseases">
              <v-flex xs12>
                <v-card class="cards">
                  <v-card-text class="diseases">{{ alldisease.name }}
                  </v-card-text>
                  <v-switch color="teal" v-model="alldisease.hasDisease" label="Add"></v-switch>
                </v-card>
                <hr>
              </v-flex>
            </v-layout>
          </v-card-text>
          <hr>
        </v-card>
        <v-footer color="teal" app>
          <span class="white--text">&copy; 2018</span>
        </v-footer>
      </v-dialog>
      <!-- End  Add diseases dialog -->

      <!--Start profile dialog-->
      <v-dialog v-model="profile_dialog" max-width="500px">
        <v-card>
          <v-card-title>
            <span><h3>User Profile</h3></span>
            <span v-show="false">ela kai pou eisai</span>
            <v-spacer></v-spacer>
          </v-card-title>
          <hr>
          <v-container grid-list-md text-xs-left>
            <v-layout row wrap>
              <v-flex xs12>
                <v-card class="user_profile">
                  <div>
                    <img class="img" v-if="!!image" v-bind:src="host + image" v-on:click.prevent="open_profile_image"/>
                    <img v-show="false" id="myimage" class="img" v-if="!!image" v-bind:src="image" @load="loaded"/>
                  </div>
                  <v-card-text class="px-15">{{'Email : ' + this.items.user.email}}</v-card-text>
                  <v-card-text class="px-15">{{'Name : ' + this.fullname}}</v-card-text>
                  <v-card-text class="px-15">{{'Amka: ' + this.amka}}</v-card-text>
                  <v-card-text class="px-15">{{'Father name: ' + this.father_name_u}}</v-card-text>
                  <v-card-text class="px-15">{{'Mother name: ' + this.mother_name_u}}</v-card-text>
                  <div id="imageapp">
                    <div v-if="!image">
                      <h2>Select an image</h2>
                      <input color="blue" type="file" height="100" width="100" @change="onFileChange">
                    </div>
                    <div v-else>
                      <v-btn color="primary" flat="flat" small="small" @click="removeImage">Remove image</v-btn>
                    </div>
                  </div>
                  <br>
                </v-card>
              </v-flex>
            </v-layout>
          </v-container>
          <hr>
          <br>
          <vue-chart class="user_chart" type="bar" :data="chartData"></vue-chart>
          <span>Diseases propability</span><br><br>
          <hr>
          <v-container grid-list-md text-xs-left class="user_profile">
            <v-layout row wrap v-for="probability in probabilitys">
              <v-flex xs12>
                <v-card class="cards">
                  <v-card-text class="px-15">{{probability.disease}} probability: {{probability.probability}}
                  </v-card-text>
                </v-card>
              </v-flex>
            </v-layout>
          </v-container>
          <v-card-actions class="user_profile">
            <v-btn color="primary" flat="flat" @click.stop="profile_dialog=false">Close</v-btn>
          </v-card-actions>
        </v-card>
      </v-dialog>
      <!--End profile dialog-->

      <!--Start about dialog-->
      <v-dialog v-model="about_dialog" max-width="500px">
        <v-card>
          <v-card-title>
            <span><h2>About Medicall App</h2></span>
            <v-spacer></v-spacer>
          </v-card-title>
          <hr>
          <v-container grid-list-md text-xs-left>
            <v-layout row wrap>
              <v-flex xs12>
                <v-card color="teal">
                  <v-card-text class="px-15"><h3>Created by<br>Vaggelis Kitsoulis<br>Hraklis Posnakidis</h3>
                  </v-card-text>
                </v-card>
              </v-flex>
            </v-layout>
          </v-container>
          <hr>
          <v-card-actions>
            <v-btn color="primary" flat="flat" @click.stop="about_dialog=false">Close</v-btn>
          </v-card-actions>
        </v-card>
      </v-dialog>
      <!--End about dialog-->

      <!-- open dialog erros -->
      <v-dialog v-model="dialogError" max-width="500px">
        <v-card>
          <v-card-title>
            <span>{{errorMsg}}</span>
            <v-spacer></v-spacer>
          </v-card-title>
          <v-card-actions>
            <v-btn color="primary" flat="flat" @click.stop="dialogError=false">Close</v-btn>
          </v-card-actions>
        </v-card>
      </v-dialog>
      <!-- end dialog errors -->

      <!--open dialog one article-->
      <v-layout row justify-center>
        <v-dialog v-model="read_dialog_article" width="600px">
          <v-card>
            <v-card-title>
              <img class="image" v-if="!!article_image" v-bind:src="host+article_image" height="100" width="100">
              <span class="headline">{{article_title}}</span>
            </v-card-title>
            <v-card-text>{{article_body}}</v-card-text>
            <v-card-actions>
              <v-spacer></v-spacer>
              <v-btn color="green darken-1" flat="flat" @click="read_dialog_article = false">Close</v-btn>
            </v-card-actions>
          </v-card>
        </v-dialog>
      </v-layout>
      <!--End dialog one article-->

      <!--Start change pasword dialog-->
      <v-form ref="form_password">
        <v-dialog v-model="password_change" max-width="500px">
          <v-card>
            <v-card-title>
              <span><h2>Change your password</h2></span>
              <v-spacer></v-spacer>
            </v-card-title>
            <hr>
            <v-container grid-list-md text-xs-left>
              <v-layout row wrap>
                <v-flex xs12>
                  <v-card class="cards">
                    <v-card-text class="px-15"><h3>{{'Email: ' + this.items.user.email}}</h3>
                      <v-text-field v-model="chpassword.user.password" prepend-icon="lock" name="password"
                                    label="Password"
                                    id="reg_password" type="password" required></v-text-field>
                      <v-text-field v-model="chpassword.user.password_confirmation" prepend-icon="lock"
                                    name="confirm password" label="Confirm Password" id="conf_password" type="password"
                                    required></v-text-field>
                      <v-text-field v-model="chpassword.user.current_password" prepend-icon="lock"
                                    name="current password"
                                    label="Current Password" id="curr_password" type="password" required></v-text-field>

                    </v-card-text>
                  </v-card>
                </v-flex>
              </v-layout>
            </v-container>
            <hr>
            <v-card-actions>
              <v-btn color="primary" flat="flat" @click.stop="password_change=false">Close</v-btn>
              <v-btn color="primary" flat="flat" @click.stop="save_change_password">Save</v-btn>
            </v-card-actions>
          </v-card>
        </v-dialog>
      </v-form>
      <!-- End Password Component -->

      <!-- start Profile image -->
      <v-form>
        <v-dialog v-model="profile_image" max-width="500px">
          <v-card>
            <v-card-title>
              <span><h2>Profile Image</h2></span>
            </v-card-title>
            <hr>
            <img class="profile_img" v-if="!!image" v-bind:src="host + image"/>
            <hr>
            <v-card-actions>
              <v-btn color="primary" flat="flat" @click.stop="profile_image=false">Close</v-btn>
            </v-card-actions>
          </v-card>
        </v-dialog>
      </v-form>
      <!-- End Profile image -->

      <!--Start add parent form-->
      <v-form ref="forms_amka">
        <v-dialog v-model="add_parent" max-width="500px">
          <v-card>
            <v-card-title>
              <span><h2>Parent Amka</h2></span>
              <v-spacer></v-spacer>
            </v-card-title>
            <hr>
            <v-container grid-list-md text-xs-left>
              <v-layout row wrap>
                <v-flex xs12>
                  <v-card class="cards">
                    <v-card-text class="px-15"><h3>{{'Name: ' + this.items.user.full_name}}</h3>
                      <div v-if="father_flag === 'false'">
                        <v-text-field v-model="parentadd.user.father_amka" prepend-icon="person" name="father"
                                      label="Father Amka" id="father_amka" type="number"></v-text-field>
                      </div>
                      <div v-else="father_flag === 'true'">
                        <v-text-field type="number" prepend-icon="person" label="Father Amka" disabled
                                      :value="this.father_amka_u"></v-text-field>
                      </div>
                      <div v-if="mother_flag === 'false'">
                        <v-text-field v-model="parentadd.user.mother_amka" prepend-icon="person" name="mother"
                                      label="Mother Amka" id="mother_amka"
                                      type="number"></v-text-field>
                      </div>
                      <div v-else="mother_flag === 'true'">
                        <v-text-field type="number" prepend-icon="person" label="Mother Amka" disabled
                                      :value="this.mother_amka_u"></v-text-field>
                      </div>
                    </v-card-text>
                  </v-card>
                </v-flex>
              </v-layout>
            </v-container>
            <hr>
            <v-card-actions>
              <v-btn color="primary" flat="flat" @click.stop="add_parent=false">Close</v-btn>
              <div v-if="father_flag === 'true' && mother_flag === 'true'">

              </div>
              <div v-else>
                <v-btn color="primary" flat="flat" @click="save_add_parent">Save</v-btn>
              </div>

            </v-card-actions>
          </v-card>
        </v-dialog>
      </v-form>
      <!--End parent add form-->

      <!--Send email to admin-->
      <v-form ref="forms_email">
        <v-dialog v-model="email_flag" max-width="500px">
          <v-card>
            <v-card-title>
              <span><h2>Contact Form</h2></span>
              <v-spacer></v-spacer>
            </v-card-title>
            <hr>
            <v-container grid-list-md text-xs-left>
              <v-layout row wrap>
                <v-flex xs12>
                  <v-card class="cards">
                    <v-card-text class="px-15">
                      <v-text-field v-model="email_json.email.subject" prepend-icon="info" name="father"
                                    label="Please write the subject" id="Subject" type="text"></v-text-field>


                      <v-flex xs8>
                        <v-text-field v-model="email_json.email.message" prepend-icon="description"
                                      name="input-7-1"
                                      label="Write your text here"
                                      multi-line
                        ></v-text-field>
                      </v-flex>


                    </v-card-text>
                  </v-card>
                </v-flex>
              </v-layout>
            </v-container>
            <hr>
            <v-card-actions>
              <v-btn color="primary" flat="flat" @click.stop="email_flag=false">Close</v-btn>
              <v-btn color="primary" flat="flat" @click="send_email_to_admin">Send</v-btn>
            </v-card-actions>
          </v-card>
        </v-dialog>
      </v-form>
      <!--Email form end-->
    </v-layout>
    <v-footer color="teal" app>
      <span class="white--text">&copy; 2018</span>
    </v-footer>
  </v-app>


</template>

<script>

  import router from '../router'
  import axios from 'axios';

  export default {
    name: 'Main',
    props: ['items'], source: String,
    data() {
      return {
        profile_image: false,
        isload:false,
        search: '',
        image:this.items.user.image_url,
        host:'http://192.168.1.7:3000',
        suggested_articles_flag: false,
        article_title:'',
        article_body:'',
        article_image:'',
        read_dialog_article: false,
        articles_dialog:false,
        main_articles:[],
        articles:this.items.articles,
        sugg_articles:this.items.user.suggested_articles,
        email_flag: false,
        f_name:'',
        m_name:'',
        father_name_u: this.items.user.father_name,
        mother_name_u: this.items.user.mother_name,
        father_amka_u: this.items.user.father_amka,
        mother_amka_u: this.items.user.mother_amka,
        father_flag: 'false',
        mother_flag: 'false',
        flag_parent: false,
        progress : true,
        dialogError : false,
        errorMsg: '',
        probabilitys: this.items.user.disease_probability,
        add_parent: false,
        password_change: false,
        add_diseases_dialog: false,
        about_dialog: false,
        profile_dialog: false,
        dialog: false,
        fullname: this.items.user.full_name,
        amka: this.items.user.amka,
        dises: this.items.user.diseases,
        alldiseases: [],
        helpdiseases: [],
        nav: true,
        msg: 'Welcome ' + this.items.user.email,
        interval: {},
        value: 0,
        drawer: false,
        fab: false,
        transition: 'slide-y-reverse-transition',
        hover: false,
        direction: 'top',
        right: true,
        left: false,
        bottom: true,
        top: false,
        lalas: [
          {message: 'Foo'},
          {message: 'Bar'}
        ],
        chpassword: {
          session: {
            email: this.items.user.email,
            authentication_token: this.items.user.authentication_token
          },
          user: {
            email: this.items.user.email,
            password: '',
            password_confirmation: '',
            current_password: ''
          }
        },
        parentadd: {
          session: {
            email: this.items.user.email,
            authentication_token: this.items.user.authentication_token
          },
          user: {
            email: this.items.user.email,
            full_name: this.items.user.full_name,
            amka: this.items.user.amka,
            father_amka: this.items.user.father_amka,
            mother_amka: this.items.user.mother_amka
          }
        },
        email_json:{
          session: {
            email: this.items.user.email,
            authentication_token: this.items.user.authentication_token
          },
          email:{
            email_address: this.items.user.email,
            subject:'',
            message:''
          }
        },
        passvalue: {
          session: {
            email: this.items.user.email,
            authentication_token: this.items.user.authentication_token
          },
          email: this.items.user.email,
          save: false,
          close: this.password_change
        },
        chartData: {
          labels: [],
          datasets:[
            {
              label: 'Disease probability',
              data: [],
              backgroundColor: []
            }
          ]
        },
        send_image: {
          session: {
            email: this.items.user.email,
            authentication_token: this.items.user.authentication_token
          },
          user:{
            email:this.items.user.email
          },
          image_data: ''
        },
        getDiseases: {
          session: {
            email: this.items.user.email,
            authentication_token: this.items.user.authentication_token
          }
        }
      }
    },
    methods: {
      disadd() {
        console.log("id diseases add: " + this.alldiseases.id);
      },
      //image method onfilechange
      onFileChange(e) {
        var files = e.target.files || e.dataTransfer.files;
        if (!files.length)
          return;

        this.createImage(files[0]);
      },
      //create image method
      createImage(file) {
        this.image = new Image();
        var reader = new FileReader();
        var vm = this;

        reader.onload = (e) => {
          vm.image = e.target.result;

        };

        reader.readAsDataURL(file);

      },
      //load image method in dom
      loaded(){
        this.isload = true;
        this.saveImage();

      },
      //save image in dom
      saveImage() {
        this.send_image.image_data = this.image;
        this.$http.post('api/users/set_image',this.send_image , {emulateJSON:true}).then(response =>{
          console.log("apo to image: "+ response.data);
          this.image = response.data.image_url;
        },(response) => {
          this.image = '';
          this.dialogError = true;
          this.errorMsg = 'Server error please try later.';
        })
      },
      //remove image
      removeImage: function (e) {
        //create json object
        let delete_image = {
              session: {
                email: this.items.user.email,
                authentication_token: this.items.user.authentication_token
              },
              user: {
                email: this.items.user.email,
                remove_image: "1"
              }
            }
        this.send_image.image_data = this.image;
        //put request to remove image
        this.$http.put('api/users/update',delete_image , {emulateJSON:true}).then(response =>{
          console.log("apo to image: "+ response.data);
          this.image = '';

        },(response) => {
          //error server
          this.dialogError = true;
          this.errorMsg = 'Server error please try later.';
        })
      },
      //open profile image
      open_profile_image(){
          this.profile_image = true;
      },
      //logout method
      logout() {
        let sign_out = {
          session: {
            email: this.items.user.email,
            authentication_token: this.items.user.authentication_token
          }
        }
        router.push({name: "lregister", params: {items: null}});
        this.$http.post('api/users/sign_out',sign_out , {emulateJSON:true}).then(response =>{
        })
      },
      //open disease history
      diseasesHistory() {
        this.dialog = true;
      },
      //open suggested articles
      open_suggested_articles(){
        this.suggested_articles_flag = true;
      },
      //open user profile
      open_user_profil() {
        if(this.father_name_u == null)
        {
          this.father_name_u = '';
        }
        if(this.mother_name_u == null)
        {
          this.mother_name_u = '';
        }

        this.profile_dialog = true;
      },
      //open all articles
      open_all_articles(){
        this.articles_dialog = true;

      },
      //read one article method
      read_one_article(article){

        this.article_title = article.title;
        this.article_body = article.body;
        this.article_image = article.image_url;
        this.read_dialog_article = true;


      },
      //open about dialog method
      open_about_dialog() {
        this.about_dialog = true;
      },
      //open add disease
      add_diseases() {
        this.add_diseases_dialog = true;
      },
      //open email form
      open_contact(){
        this.email_flag = true;
        this.$refs.forms_email.reset();
      },
      //sen email to admin method
      send_email_to_admin(){
        this.email_flag = false;
        //post request
        this.$http.post('/api/contact_form', this.email_json, {emulateJSON: true}).then(response => {
          this.$refs.forms_email.reset();
          this.dialogError = true;
          this.errorMsg = response.data.success;
        },(response) =>{
          this.dialogError = true;
          this.errorMsg = 'Server error please try later.';
        })

      },
      //save disease from user
      save_diseases_user(alldiseases) {
        //create json object
        let passdiseases =  {
          session: {
            email: this.items.user.email,
            authentication_token: this.items.user.authentication_token
          },
          user: {
            email: this.items.user.email,
            diseases: []
          }
        };

        alldiseases.forEach(disease => {
          if (disease.hasOwnProperty('hasDisease') && disease.hasDisease) {
            passdiseases.user.diseases.push(disease.id)
          }
        });

        this.$http.put('api/users/update_history', passdiseases, {emulateJSON: true}).then(response => {
          console.log("save diseases: " + response.data);

          //for suggested articles
          this.sugg_articles = response.data.suggested_articles;
          this.image = response.data.image_url

          console.log('ta protinomena arthra: ' + this.sugg_articles[0].title);
          //End suggested articles

          this.dialogError = true;
          this.errorMsg = 'Diseases add';

          for(var i=0;i< passdiseases.user.diseases.length;i++){
            for(var j = 0;j< this.alldiseases.length; j++){
              if(passdiseases.user.diseases[i] == this.alldiseases[j].id){

                for(var k = 0; k < this.probabilitys.length; k++){
                  if(this.probabilitys[k].disease == this.alldiseases[j].name){
                    this.probabilitys.splice(k,1);
                    this.chartData.labels.splice(k,1);
                    this.chartData.datasets[0].data.splice(k,1);
                  }
                }
                this.dises.push(this.alldiseases[j]);
                this.alldiseases.splice(j, 1);
              }
            }
          }
        },(response) =>{
          this.dialogError = true;
          this.errorMsg = 'Server error please try later.';
        })
        this.add_diseases_dialog = false;
      },
      //open password change dialog
      open_password_change() {
        this.password_change = true;
        this.$refs.form_password.reset();
      },
      //save change password method
      save_change_password() {
        //validation field
        if(this.chpassword.user.password == null || this.chpassword.user.password == '' || this.chpassword.user.password_confirmation == '' || this.chpassword.user.password_confirmation== null || this.chpassword.user.current_password == '' || this.chpassword.user.current_password == null){
          this.dialogError = true;
          this.progress = false;
          this.errorMsg = 'Please complete all required fields and try again.'
          this.$refs.form_password.reset();
        }
        else if(this.chpassword.user.password.length < 6 || this.chpassword.user.current_password.length < 6 || this.chpassword.user.current_password.length < 6){
          this.dialogError = true;
          this.progress = false;
          this.errorMsg = 'Passwords must be at least 6 characters'
          this.$refs.form_password.reset();
        }
        else if(this.chpassword.user.password != this.chpassword.user.password_confirmation){
          this.dialogError = true;
          this.progress = false;
          this.errorMsg = 'Incorrect password confirmation';
          this.$refs.form_password.reset();
        }
        else {
          this.progress = true;
          //create request
          this.$http.put('/api/users/change_password', this.chpassword, {emulateJSON: true}).then(response => {

            if(response.data.email == this.items.user.email){
              this.password_change = false;
              this.dialogError = true;
              this.errorMsg = 'Password changed';
            }
            else {
              this.password_change = false;
              this.dialogError = true;
              this.errorMsg = response.data.error;
            }
          },(response) => {
            this.password_change = false;
            this.dialogError = true;
            this.errorMsg = 'Server error please try later.';
          })
        }
      },
      //open add parent dalog
      open_add_parent() {
        this.add_parent = true;
        this.$refs.forms_amka.reset();
        if((!!this.father_name_s) || (!!this.father_name_u))
        {

        }
        if(!!this.father_amka_u)
        {
          this.father_flag = 'true';
        }
        if(!!this.mother_amka_u)
        {
          this.mother_flag = 'true';
        }
      },
      //save add parent method
      save_add_parent() {

        if(this.parentadd.user.father_amka == null)
        {
          this.parentadd.user.father_amka = this.father_amka_u
        }
        if(this.parentadd.user.mother_amka == null)
        {
          this.parentadd.user.mother_amka = this.mother_amka_u;
        }
        //parent amka validation
        if((this.parentadd.user.father_amka == null || this.parentadd.user.father_amka == '')
          && (this.parentadd.user.mother_amka == null || this.parentadd.user.mother_amka == '')){
          this.dialogError = true;
          this.$refs.forms_amka.reset();
          this.errorMsg = 'Amka is empty.Please complete at least one field '
        }
        else if((!!this.parentadd.user.father_amka) && (this.parentadd.user.father_amka == this.mother_amka_u)){
            this.dialogError = true;
            this.$refs.forms_amka.reset();
            this.errorMsg = 'Fathers amka can not be the same as his mothers amka'
        }
        else if((!!this.parentadd.user.mother_amka) && (this.parentadd.user.mother_amka == this.father_amka_u)){
          this.dialogError = true;
          this.$refs.forms_amka.reset();
          this.errorMsg = 'Μothers amka can not be the same as his fathers amka'
        }
        else if(this.parentadd.user.father_amka == this.parentadd.user.mother_amka){
            this.dialogError = true;
            this.$refs.forms_amka.reset();
            this.errorMsg = 'Fathers and mothers amka is the same'
        }
        else if((!!this.parentadd.user.father_amka  ) && ((isNaN(this.parentadd.user.father_amka) == true)
          || this.parentadd.user.father_amka.length < 11)){
            this.dialogError = true;
            this.$refs.forms_amka.reset();
            this.errorMsg = 'Father amka is invalid.Please try again'
        }
        else  if((!!this.parentadd.user.mother_amka )  && ((isNaN(this.parentadd.user.mother_amka) == true)
          || this.parentadd.user.mother_amka.length < 11)){
          this.dialogError = true;
          this.$refs.forms_amka.reset();
          this.errorMsg = 'Mother amka is invalid.Please try again'
        }
        else if(this.parentadd.user.father_amka == this.items.user.amka || this.parentadd.user.mother_amka == this.items.user.amka){
          this.dialogError = true;
          this.$refs.forms_amka.reset();
          this.errorMsg = 'Invalid amka.Please add parent amka'
        }
        else
        {
          this.$http.put('/api/users/update', this.parentadd, {emulateJSON: true}).then(response => {
            console.log(response.data);
            console.log(this.chpassword);
            this.father_name_u = response.data.father_name;
            this.mother_name_u = response.data.mother_name;
            this.father_amka_u = response.data.father_amka;
            this.mother_amka_u = response.data.mother_amka;
            this.dialogError =true;
            this.errorMsg = "Parent Add"

            this.probabilitys =  response.data.disease_probability;
            this.chartCreate();
            if(response.data.email == this.items.user.email){
              this.add_parent = false;
              this.$refs.forms_amka.reset();
              this.dialogError = true;
              this.errorMsg = 'Parent AMKA add';
              this.flag_parent = true;

              console.log("save add parent prin" + this.probabilitys);
              this.probabilitys = response.data.disease_probability;
              for(var i = 0 ; i < this.probabilitys.length; i++)
              {

              }
              this.chartCreate();
            }

            if(!!response.data.father_name)
            {
              this.father_flag = 'true';
            }
            if(!!response.data.mother_name)
            {
              this.mother_flag = 'true';
            }
          },(response) => {
            this.add_parent = false;
            this.dialogError = true;
            this.errorMsg = 'Server error please try later.';
          })
        }
      },
      //diseases method
      test() {
        this.$http.post('/api/diseases', this.getDiseases, {emulateJSON: true}).then(response => {

          this.helpdiseases = response.data;
          for (var i = 0; i < this.dises.length; i++) {

            for (var j = 0; j < this.helpdiseases.length; j++) {
              if (this.dises[i].name == this.helpdiseases[j].name) {
                this.helpdiseases.splice(j, 1);
              }
            }

          }
          this.alldiseases = this.helpdiseases;
        })
      },
      //create chart in profile
      chartCreate(){

        for(var i = 0;i < this.probabilitys.length;i++){
          this.chartData.labels[i] = this.probabilitys[i].disease;
          this.chartData.datasets[0].data[i]= this.probabilitys[i].probability;

          if(this.probabilitys[i].probability <= 10.0){
            this.chartData.datasets[0].backgroundColor[i] = '#FFEBEE';
          }
          else if(this.probabilitys[i].probability <= 20.0){
            this.chartData.datasets[0].backgroundColor[i] = '#FFCDD2';
          }
          else if(this.probabilitys[i].probability <= 30.0){
            this.chartData.datasets[0].backgroundColor[i] = '#EF9A9A';
          }
          else if(this.probabilitys[i].probability <= 40.0){
            this.chartData.datasets[0].backgroundColor[i] = '#E57373';
          }
          else if(this.probabilitys[i].probability <= 50.0){
            this.chartData.datasets[0].backgroundColor[i] = '#EF5350';
          }
          else if(this.probabilitys[i].probability <= 60.0){
            this.chartData.datasets[0].backgroundColor[i] = '#F44336';
          }
          else if(this.probabilitys[i].probability <= 70.0){
            this.chartData.datasets[0].backgroundColor[i] = '#E53935';
          }
          else if(this.probabilitys[i].probability <= 80.0){
            this.chartData.datasets[0].backgroundColor[i] = '#D32F2F';
          }
          else if(this.probabilitys[i].probability <= 90.0){
            this.chartData.datasets[0].backgroundColor[i] = '#C62828';
          }
          else{
            this.chartData.datasets[0].backgroundColor[i] = '#D50000';
          }
        }
      },
      //password validation
      passwordValidation(){
        if(this.chpassword.user.password == null || this.chpassword.user.password == '' || this.chpassword.user.password_confirmation == '' || this.chpassword.user.password_confirmation== null || this.chpassword.user.current_password == '' || this.chpassword.user.current_password == null){
          this.dialogError = true;
          this.$refs.form.reset();
          this.progress = false;
          this.errorMsg = 'Please complete all required fields and try again.'
        }
        else if(this.chpassword.user.password.length < 6 || this.chpassword.user.current_password.length < 6 || this.chpassword.user.current_password.length < 6){
          this.dialogError = true;
          this.progress = false;
          this.$refs.form.reset();
          this.errorMsg = 'Passwords must be at least 6 characters'
        }
        else if(this.chpassword.user.password != this.chpassword.user.password_confirmation){
          this.dialogError = true;
          this.$refs.form.reset();
          this.progress = false;
          this.errorMsg = 'Incorrect password confirmation';
        }
      },
      //all articles method
      all_articles(){
        for(var i=0;i< 2; i++){
          this.main_articles[i] = this.articles[i];
        }
      }
    },
    //on create page
    created: function () {
      this.all_articles();
      this.test();
      this.chartCreate();
      this.father_name_u = this.items.user.father_name;
      this.mother_name_u = this.items.user.mother_name;
    },
    //search
    computed: {
      filteredArticles: function() {
        return this.articles.filter((article) =>{
          return article.title.match(this.search);
        });

      }
    }
  }
</script>

<style scoped>
  h1, h2 {
    font-weight: normal;
  }

  ul {
    list-style-type: none;
    padding: 0;
  }

  li {
    display: inline-block;
    margin: 0 10px;
  }

  a {
    color: #42b983;
  }

  .progress {
    margin-top: 300px;
  }

  .navi {
    /*margin-left: 200px;*/
    /*margin-right: 10px;*/
    /*padding-right: 200px;*/
  }

  .welc {

  }

  .button {
    margin-bottom: 30px;
  }

  .px-15 {
    color: black;
    margin-bottom: 1px;
    border-style: inset;
    background-color: #f5f5f5;

  }
  .top_news {
    color: black;
    margin-bottom: 1px;
  }

  .px-16 {
    padding-top: auto;
    padding-bottom: 200px;
    padding-top: 1px;
    height: 230px;
  }
  .cards{
    background-color: #f5f5f5;
    border-style: outset;
  }

  .bar {
    margin-left: 5px;
  }
  .profile {
    margin-left: 0px;
  }
  .settings {
    margin-left: 2px;
  }
  .articles {
    margin-left: -1px;
  }
  .about {
    margin-left: -5px;
  }
  .image {
    display: block;
    margin-left: auto;
    margin-right: auto;
  }


  .img {
    text-align: center;
    border-radius: 50%;
    width: 30%;
    margin: auto;
    display: block;
    margin-bottom: 10px;
  }
  .profile_img {
    border-radius: 8px;
    max-width: auto;
    max-height: auto;
  }
  button {

  }

  .search {
    padding-left: 10px;
    padding-right: 10px;
    background-color: #fafafa;
  }
  .scrollbar {
    max-height: 10px;
  }
  .user_profile {
    background-color: whitesmoke;
  }
  .diseases {
    background-color: #e0e0e0;
    border-style: initial;
  }
  .article_title {
    /*background-color: #e0e0e0;*/

    color: black;
  }
  .user_chart {
    background-color: whitesmoke;
  }

</style>
