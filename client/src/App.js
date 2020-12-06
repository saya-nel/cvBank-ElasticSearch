import React, { Component } from "react";
import { Form, Button } from "react-bootstrap";
import axios from "axios";


export default class App extends Component {

  constructor(props) {
    super(props);
    this.state = {
      file: null,
      gotError: true,
      tag: "", 
      cvs: []
    };
  }

  getBase64(file) {
    return new Promise((resolve, reject) => {
      const reader = new FileReader();
      reader.readAsDataURL(file);
      reader.onload = () => resolve(reader.result);
      reader.onerror = error => reject(error);
    });
  }

  checkForm = () => {
    this.setState({
      gotError: this.state.file === null 
    });
  }

  handleChange = async e => {
    e.preventDefault();
    if (e.target.files != null && e.target.files.length > 0) {
      const name = e.target.files[0].name.split(".")[0];
      const base64 = await this.getBase64(e.target.files[0]);
      
      const bin = atob(base64.split(",")[1]);
      let pdfjsLib = window['pdfjs-dist/build/pdf'];
      pdfjsLib.GlobalWorkerOptions.workerSrc = '//mozilla.github.io/pdf.js/build/pdf.worker.js';
      let loadingTask = pdfjsLib.getDocument({data:bin});
      let pdf = await loadingTask.promise;
      let nbPages = pdf._pdfInfo.numPages;
  
      let tags = [];
      for (let i = 0; i < nbPages; i++) {
        let currentPageText = await (await pdf.getPage(1)).getTextContent();
        for (let j = 0; j < currentPageText.items.length; j++) {
          let currentLine = currentPageText.items[j].str;
          // suppression de tout les caractères non alphabetiques (sauf accents)
          currentLine = currentLine.replace(/[^A-zÀ-ú]/gi, ' ');
          let currentLineSplited = currentLine.split(" ");
          let currentLineFiltered = currentLineSplited.filter(e => {return (e.length > 0)});
          currentLineFiltered.forEach(e => tags.push(e));
        }
      }

      this.setState({
        file: {
          name: name,
          base64: base64,
          tags: tags
        }
      }, () => {
        this.checkForm();
      })
    }
    else {
      if (e.target.name === "tag")
      this.setState({
        tag: e.target.value
      });
      else {
        this.setState({
          file:null
        }, () => {this.checkForm()});
      }
    }
  }

  handleSubmitCv = (e) => {
    e.preventDefault();
    axios.post("/cv", this.state.file).catch(err => {console.log(err);});
  }

  handleSubmitSearch = (e) => {
    e.preventDefault();
    axios.get("/cv", {params: {tag: this.state.tag}})
    .then(res => {this.setState({cvs : res.data})})
    .catch(err => {console.log(err);});
  }

  render() {
    return (
      <div>
        <Form onSubmit={this.handleSubmitCv}>
          <Form.Group>
            <Form.Label>Choisir un CV au format pdf</Form.Label>
            <Form.File
              id="pdf"
              accept=".pdf"
              data-browse="Parcourir"
              onChange={this.handleChange}
            />
          </Form.Group>
          <Button type="submit" variant="dark" disabled={this.state.gotError}>
            Enregistrer
          </Button>
        </Form>

        <br/><br/>

        <Form onSubmit={this.handleSubmitSearch}>
          <Form.Group>
            <Form.Label>Entrer un mot clé</Form.Label>
            <Form.Control type="textarea" id="tag" name="tag" placeholder="tag" value={this.state.tag} onChange={this.handleChange} maxLength="20"/>
            <Button type="submit" variant="dark" disabled={this.state.tag.length <= 0}>Chercher</Button>
          </Form.Group>
        </Form>
        {
          this.state.cvs.length > 0 ?(<div>Résultats de la recherche :</div>):(<div>Aucun résultats de recherche</div>)
        }
        {
          this.state.cvs.map((cv,i) => {
            return (
              <div key={i}><a download={cv.name} href={cv.base64}>{cv.name + ".pdf"}</a></div>
            );
          })
        }
      </div>
    )
  }
}