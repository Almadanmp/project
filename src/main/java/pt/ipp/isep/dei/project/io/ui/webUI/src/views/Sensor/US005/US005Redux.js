import React from 'react';
import {connect} from 'react-redux';
import {addSensorType} from './Actions005';
import {Button} from "reactstrap";

class US005Redux extends React.Component {

  constructor(props) {
    super(props);
    this.handleSubmit = this.handleSubmit.bind(this);
    this.state = {
      name: '',
      units: ''
    };

    this.handleInputChange = attribute => event => {
      this.setState({
        [attribute]: event.target.value
      });
    };

  }

  handleSubmit() {
    this.props.onPostType(this.state);
  }

  render() {
    const {name, units} = this.state;
    return (
      <>
        <label className="form-style-10"> Name:<span>  </span>
          <input  value={this.state.name} type="text" name="name" placeholder="Name of the new sensor type"
                 onChange={this.handleInputChange('name')}/>
        </label>
        <p></p>
        <label className="form-style-10"> Units:<span>  </span>
          <input value={this.state.units} type="text" name="units" placeholder="Unit measure used for this type"
                 onChange={this.handleInputChange('units')}/>
        </label>
        <p/>
        <button className="bubbly-button" onClick={this.handleSubmit}>Add sensor
          type</button>
      </>
    );
  }
}


const mapDispatchToProps = (dispatch) => {
  return {
    onPostType: ({name, units}) => {
      dispatch(addSensorType(name, units))
    }
  }
};

export default connect(
  null,
  mapDispatchToProps
)(US005Redux);
