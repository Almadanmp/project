import React, {Component} from 'react';
import {connect} from 'react-redux';
import {fetchGA} from './Actions003';
import {Button} from "reactstrap";

class US003Redux extends Component {
  constructor(props) {
    super(props);
    this.handleSubmit = this.handleSubmit.bind(this);
    this.state = {
      id: 0,
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
    return (
      <>
        <label> Id:
          <input value={this.state.id} type="number" name="id" onChange={this.handleInputChange('id')}/>
        </label>

        <label> Name:
          <input value={this.state.name} type="text" name="name" onChange={this.handleInputChange('name')}/>
        </label>

        <label> Type area:
          <input value={this.state.typeArea} type="text" name="typeArea" onChange={this.handleInputChange('typeArea')}/>
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
          <input value={this.state.description} type="text" name="description" onChange={this.handleInputChange('description')}/>
        </label>

        <p>The geographic area to be created has the following
          details: {this.state.id + ', ' + this.state.name + ', ' + this.state.typeArea + this.state.length + this.state.width + ', '
          + this.state.latitude + ', ' + this.state.longitude + ', ' + this.state.altitude + this.state.description + '.'}</p>
        <Button style={{backgroundColor: '#e4e5e6', marginBottom: '1rem'}} onClick={this.handleSubmit}>Save new
          geographic area</Button>
      </>
    );
  }
}

const mapStateToProps = (state) => {
  return {
    geographicAreaInfo: state.Reducer003.geographicAreaInfo
  }
};

const mapDispatchToProps = (dispatch) => {
  return {
    onFetchArea: (id, name, typeArea, length, width, latitude, longitude, altitude, description) => {
      dispatch(fetchGA({id, name, typeArea, length, width, latitude, longitude, altitude, description}))
    }

  }
};

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(US003Redux);
