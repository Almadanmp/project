import React, {Component} from 'react';
import {connect} from 'react-redux';
import {fetchGABTs} from './Actions004';

class US004Redux extends Component {
  constructor(props) {
    super(props);
  }

  componentDidMount() {
    this.props.onFetchByTypes();
  }

  organizeByType(areas) {
    var areasByType = {};
    for (let area of areas) {
      const areaType = Object.keys(areasByType).find(x => x === area.typeArea);
      if (areaType) {
        areasByType[area.typeArea].push(area);
      } else {
        areasByType[area.typeArea] = [area];
      }
    }
    return areasByType;
  }

  render() {
    const {areas} = this.props;
    var areasByType = this.organizeByType(areas);
    return (
      <>
        <label>
          {Object.keys(areasByType).map(type => (
            <div>
              <h5>{'Areas from type ' + type + ': '}</h5>
              {areasByType[type].map(area => (<p>{'- ' + area.name + ' - description: ' + area.description}</p>))}
            </div>
          ))}
        </label>
      </>
    );
  }
}

const mapStateToProps = (state) => {
  return {
    areas: state.Reducers004.areas
  }
};

const mapDispatchToProps = (dispatch) => {
  return {
    onFetchByTypes: () => {
      dispatch(fetchGABTs())
    }

  }
};

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(US004Redux);
