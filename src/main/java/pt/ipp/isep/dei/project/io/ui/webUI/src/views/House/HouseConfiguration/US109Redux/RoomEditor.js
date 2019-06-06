import React from 'react';
import 'react-day-picker/lib/style.css';
import {connect} from 'react-redux';
import US109Button from './US109Button';

class RoomEditor extends React.Component {

  constructor(props) {
    super(props);
    this.handleSubmit = this.handleSubmit.bind(this);
    this.state = {
      name:this.props.name,
      floor: 0,
      width: 0,
      length: 0,
      height: 0,
    };
  }

    handleInputChange = attribute => event => {
      this.setState({
        [attribute]: event.target.value
      });
    };


  handleSubmit(){
    this.props.onFetchSensor(this.state);
  }


  render() {
    const {floor, width,length,height} = this.state;
    return (
      <div>
        <label>Floor:
        <input value={floor} type="text" name="sensorId" onChange={this.handleInputChange('floor')}/>
        </label>
        <p></p>
        <label>Width:
          <input value={width} type="text" name="width" onChange={this.handleInputChange('width')}/>
        </label>
          <p></p>
        <label>Length:
          <input value={length} type="text" name="length" onChange={this.handleInputChange('length')}/>
        </label>
        <p></p>
        <label>Height:
        <input value={height} type="text" name="height" onChange={this.handleInputChange('height')}/>
      </label>
          <US109Button name={this.props.name} floor={this.state.floor} width={this.state.width} length={this.state.length} height={this.state.height}/>
      </div>
    )
  }
}

export default RoomEditor
