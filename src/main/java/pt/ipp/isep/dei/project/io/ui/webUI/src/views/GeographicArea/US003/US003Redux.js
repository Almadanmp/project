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
      latitude: 0,
      longitude: 0,
      altitude: 0
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
    const {id, name, typeArea, latitude, longitude, altitude} = this.props;
    return (
      <>
        <label> Id:
          <input value={id} type="number" name="id" onChange={this.handleInputChange('id')}/>
        </label>

        <label> Name:
          <input value={this.state.name} type="text" name="name" onChange={this.handleInputChange('name')}/>
        </label>

        <label> Type area:
          <input value={this.state.typeArea} type="text" name="typeArea" onChange={this.handleInputChange('typeArea')}/>
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

        <p>The geographic area to be created has the following
          details: {this.state.id + ', ' + this.state.name + ', ' + this.state.typeArea + ', ' + this.state.latitude + ', ' + this.state.longitude + ', ' + this.state.altitude + '.'}</p>
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
    onFetchArea: (id, name, typeArea, latitude, longitude, altitude) => {
      dispatch(fetchGA({id, name, typeArea, latitude, longitude, altitude}))
    }

  }
};

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(US003Redux);
