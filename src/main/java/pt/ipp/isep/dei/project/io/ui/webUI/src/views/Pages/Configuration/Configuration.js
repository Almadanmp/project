import React, {Component} from 'react';
import ImportGA from "./ImportGA";
import FileUploaderOne from "./uploadOne/FileUploader";

class Configuration extends Component {
  constructor(props) {
    super(props);
    this.toggle = this.toggle.bind(this);
    this.state = {collapse: false};
  }

  toggle() {
    this.setState(state => ({collapse: !state.collapse}));
  }

  render() {
    return (
      <div>
        <h2>Welcome to the Application Configuration Menu</h2>
        <h4>Please select the option you want to run</h4>
        <h6>Import Geographic Areas or/and Area Sensors:</h6>
        <ImportGA/>
        <FileUploaderOne/>
      </div>
    );
  }
}

export default Configuration;

