import React from 'react';
import {connect} from 'react-redux';
import {fetchGA} from './Actions003';
import {Button} from "reactstrap";

class US003Redux extends React.Component {
  constructor(props) {
    super(props);
    this.handleSubmit = this.handleSubmit.bind(this);
    this.state = {
      name: '',
      typeArea: '',
      length: 0,
      width: 0,
      latitude: 0,
      longitude: 0,
      altitude: 0,
      description: ''
    };

    this.handleInputChange = attribute => event => {
      this.setState({
        [attribute]: event.target.value
      });
    };

  }

  handleSubmit() {
    this.props.onFetchArea(this.state);
  }

  render() {
    const {name, typeArea, length, width, latitude, longitude, altitude, description} = this.state;
    return (
      <>
        <label> Name:
          <input value={this.state.name} type="text" name="name" placeholder="Geographic area name" onChange={this.handleInputChange('name')}/>
        </label>

        <label> Type area:
          <input value={this.state.typeArea} type="text" name="typeArea" placeholder="Area type" onChange={this.handleInputChange('typeArea')}/>
        </label>

        <label> Length:
          <input value={this.state.length} type="number" name="length" onChange={this.handleInputChange('length')}/>
        </label>

        <label> Width:
          <input value={this.state.width} type="number" name="width" onChange={this.handleInputChange('width')}/>
        </label>

        <label> Latitude:
          <input value={this.state.latitude} type="number" name="latitude" onChange={this.handleInputChange('latitude')}/>
        </label>

        <label> Longitude:
          <input value={this.state.longitude} type="number" name="longitude" onChange={this.handleInputChange('longitude')}/>
        </label>

        <label> Altitude:
          <input value={this.state.altitude} type="number" name="altitude" onChange={this.handleInputChange('altitude')}/>
        </label>

        <label> Description:
          <input value={this.state.description} type="text" name="description" placeholder="Area details" onChange={this.handleInputChange('description')}/>
        </label>

        <p>The geographic area to be created has the following
          details: {name + ', ' + typeArea + ', ' + length + ', ' + width + ', '
          + latitude + ', ' + longitude + ', ' + altitude + ', ' + description + '.'}</p>
        <Button style={{backgroundColor: '#e4e5e6', marginBottom: '1rem'}} onClick={this.handleSubmit}>Save new
          geographic area</Button>
      </>
    );
  }
}


const mapDispatchToProps = (dispatch) => {
  return {
    onFetchArea: ({name, typeArea, length, width, latitude, longitude, altitude, description}) => {
      dispatch(fetchGA({name, typeArea, length, width, latitude, longitude, altitude, description}))
    }
  }
};

export default connect(
  null,
  mapDispatchToProps
)(US003Redux);
