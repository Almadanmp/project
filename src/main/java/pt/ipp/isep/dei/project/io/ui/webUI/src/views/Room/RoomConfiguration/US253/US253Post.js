import React, {Component} from 'react';


class US253Post extends Component {

  constructor(props) {
    super(props);
    this.state = {
      item: [],
    }
  }


  componentDidMount() {
    const sensorId = this.props.sensorId;
    const name = this.props.name;
    const dateStartedFunctioning = this.props.dateStartedFunctioning;
    const typeSensor = this.props.typeSensor;
    console.log(this.props);
    fetch('http://localhost:9898/roomConfiguration/rooms/' + this.props.roomID + '/sensors', {
      method: 'post',
      headers: {'Content-Type': 'application/json'},
      body: JSON.stringify({sensorId, name, dateStartedFunctioning, typeSensor})
    })
      .then(res => res.json())
      .then((json) => {
        this.setState({
          item: json,
        })
      })
      .catch(console.log);
  };


  render() {
    return (
      <div>
        <p>The created sensor has the following
          configuration: {this.props.roomID}, {this.props.sensorId}, {this.props.name},{this.props.dateStartedFunctioning}, {this.props.typeSensor} </p>
      </div>
    );


  }
}

export default US253Post;
