import React, {Component} from 'react';

class ImportGA extends Component {
  constructor(props) {
    super(props);
    this.toggle = this.toggle.bind(this);
    this.state = {collapse: false, name: 'geographicAreasAndSensors'};
  }

  toggle() {
    this.setState(state => ({collapse: !state.collapse}));
  }

  render() {
    return (
      <div>
        <button className="bubbly-button" onClick={this.toggle}> Import geographic areas and sensors</button>
      </div>
    );
  }

}

export default ImportGA;
